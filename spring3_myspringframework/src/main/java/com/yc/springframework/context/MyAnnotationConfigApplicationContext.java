package com.yc.springframework.context;

import com.yc.springframework.stereotype.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class MyAnnotationConfigApplicationContext implements MyApplicationContext{
    private Map<String,Object> beanMap=new HashMap<String,Object>();

    public MyAnnotationConfigApplicationContext(Class<?>... componentClasses) {
        try {
            register(componentClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register(Class<?>[] componentClasses) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException, ClassNotFoundException {
        if(componentClasses==null||componentClasses.length<=0){
            throw new RuntimeException("没有指定配置类") ;
        }
        for(Class cls:componentClasses){
            //请实现这个里面的代码

            //1.只实现IOC MyPostConstruct MyPreDestroy
            if(!cls.isAnnotationPresent(MyConfiguration.class)){
            /**/    continue;
            }
            String [] basePackages=getAppConfigBasePackages(cls);
            if(cls.isAnnotationPresent(MyComponentScan.class)){
                MyComponentScan mcs=(MyComponentScan)cls.getAnnotation(MyComponentScan.class);
                if(mcs.basePackages()!=null&&mcs.basePackages().length>0){
                    basePackages= mcs.basePackages();
                }
            }

            //处理@MyBean的情况
            Object obj= cls.newInstance(); //Obj就是当前解析的 MyAPPConfig对象
            handleMyBean(cls,obj);

            //处理basePackages基础包下的所有的托管bean
            for(String basePackage:basePackages){
                scanPackageAndSubPackageClass(basePackage);
            }
            //继续托管其他的bean
            handleMangedBean();
            //2 实现di 循环 beanMap中的每个bean 找到他们每个类中的每个由AutoWired注解的方法以实现DI;
            handleDi(beanMap);




        }
    }

    /**
     * 处理AutoWired
     * @param beanMap
     */
    private void handleDi(Map<String, Object> beanMap) throws InvocationTargetException, IllegalAccessException {
        Collection <Object> objectCollection=beanMap.values();
        for (Object obj:objectCollection){
            Class c=obj.getClass();
            Method [] ms=c.getDeclaredMethods();
            for (Method m:ms) {
                if (m.isAnnotationPresent(MyAutowired.class)&&m.getName().startsWith("set")) {

                    invokeAutoWiredMethod(m,obj);
                }else if(m.isAnnotationPresent(MyResource.class)&&m.getName().startsWith("set")){
                    invokeResourceMethod(m,obj);
                }
            }
            Field[] fl=c.getDeclaredFields();
            for (Field f:fl) {
                if (f.isAnnotationPresent(MyAutowired.class)) {

                }else if(f.isAnnotationPresent(MyResource.class)){

                }
            }

        }
    }

    private void invokeResourceMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //取出 MyResource中的name属性值，当成beanId
        MyResource mr=m.getAnnotation(MyResource.class);
        String beanId=mr.name();
        //如果没有 则取出 m方法中参数的类型名，改成首字小写，当成beanId
        if(beanId==null||beanId.equalsIgnoreCase("")){
            String pname=m.getParameterTypes()[0].getSimpleName();
            beanId=pname.substring(0,1).toLowerCase()+pname.substring(1);
        }
        //从beanMap取出
        Object o=beanMap.get(beanId);
        //invoke
        m.invoke(m,o);
        
    }

    private void invokeAutoWiredMethod(Method m,Object obj) throws InvocationTargetException, IllegalAccessException {
        //取出 m的参数类型
        Class typeClass=m.getParameterTypes()[0];
        //从beanMap中循环所有的Object
        Set<String> keys=beanMap.keySet();
        for(String key:keys){
            //如果是  则从beanMap中取出
            Object o=beanMap.get(key);
            //判断 这些object是否为 参数类型的实例 instanceOf
            Class[] interfaces=o.getClass().getInterfaces();

            for(Class c:interfaces){
                //System.out.println(c.getName()+"\t"+typeClass);
                if(c==typeClass){
                    //if(o.getClass().getName().equalsIgnoreCase(typeClass.getName())){
                    m.invoke(obj,o);
                    break;
                }
            }

        }


        //invoke();
        //m.invoke(obj,);
    }
    /**
     *处理MangedBeanClasss 所有的Class类，筛选出所有的@Component等类 并实例化 存到beanMap中
     */
    private void handleMangedBean() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        for(Class c:managedBeanClasses){
            if(c.isAnnotationPresent(MyComponent.class)){
                saveMangedBean(c);
            }else if(c.isAnnotationPresent(MyService.class)){
                saveMangedBean(c);
            }else if(c.isAnnotationPresent(MyRepository.class)){
                saveMangedBean(c);
            }else if(c.isAnnotationPresent(MyController.class)){
                saveMangedBean(c);
            }else {
                continue;
            }
        }
    }
    private void saveMangedBean(Class c) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o=c.newInstance();
        handlePostConstruct(o,c);
        String beanId=c.getSimpleName().substring(0,1).toLowerCase(Locale.ROOT)+c.getSimpleName().substring(1);
        beanMap.put(beanId,o);
    }

    /**
     * 处理basePackages基础包下的所有的托管bean
     * @param basePackage
     */
    private void scanPackageAndSubPackageClass(String basePackage) throws IOException, ClassNotFoundException {
        String packagePath=basePackage.replaceAll("\\.","/");
        System.out.println("扫描包路径:"+packagePath);
        Enumeration<URL> files=Thread.currentThread().getContextClassLoader().getResources(packagePath);
        while (files.hasMoreElements()){
            URL url=files.nextElement();
            System.out.println("配置的扫描路径为："+url.getFile());
            //TODO:递归这些目录，查找 .class文件
            findClassesInPackages(url.getFile(),basePackage);
        }
    }

    private Set<Class> managedBeanClasses=new HashSet<Class>();

    /**
     * 查找file 下面及子包所有托管的class ，存到一个Set(managedBeanClasses)中，
     * @param file
     * @param basePackage
     */
    private void findClassesInPackages(String file, String basePackage) throws ClassNotFoundException {
        File f=new File(file);
        File[] classFiles=f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file1) {
                return file1.getName().endsWith(".class")||file1.isDirectory();
            }
        });
        for(File cf:classFiles){
            if(cf.isDirectory()){
                //如果是目录  递归
                //拼接子目录
                basePackage+="."+cf.getName().substring(cf.getName().lastIndexOf("/")+1);
                findClassesInPackages(cf.getAbsolutePath(),basePackage);
            }else {
                //加载cf作为Class文件
                URL[] urls=new URL[]{  };
                URLClassLoader ucl=new URLClassLoader(urls);
                //com.yc.bean.HelloWorld.class->com.yc.bean.HelloWorld
                Class c=ucl.loadClass(basePackage+"."+cf.getName().replace(".class",""));
                managedBeanClasses.add(c);

            }
        }
    }

    //real money generator
    /**处理MyAPPConfig配置类中的@Bean注解
     * @param cls
     * @param obj
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void handleMyBean(Class cls,Object obj) throws InvocationTargetException, IllegalAccessException {
        Method []ms=cls.getDeclaredMethods();
        for(Method m:ms){
            if(m.isAnnotationPresent(MyBean.class)){
                Object o=m.invoke(obj);
                //TODO:加入处理  @MyBean注解对应的方法所实例化的类中的@MyPostConstruct对应的方法
                handlePostConstruct(o,o.getClass()); //o在这指HelloWorld对象   o.getClass（）对应
                beanMap.put(m.getName(),o);


            }
        }
    }

    /**
     * 处理一个@bean中的MyPreDestroy对应的方法
     * @param o
     * @param aClass
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void handlePreDestroy(Object o, Class<?> aClass) throws InvocationTargetException, IllegalAccessException {
        Method []ms=aClass.getDeclaredMethods();
        for(Method m:ms){
            if(m.isAnnotationPresent(MyPreDestroy.class)){
                m.invoke(o);
            }
        }
    }

    /**
     * 处理一个@bean中的MyPostConstruct对应的方法
     * @param o
     * @param cls
     */
    private void handlePostConstruct(Object o, Class<?> cls) throws InvocationTargetException, IllegalAccessException {
        Method []ms=cls.getDeclaredMethods();
        for(Method m:ms){
            if(m.isAnnotationPresent(MyPostConstruct.class)){
                m.invoke(o);
            }
        }
    }

    /**
     * 获取AppConfig类所在的包路径
     * @return
     */
    private String[] getAppConfigBasePackages(Class cls) {
        String [] paths=new String[1];
        paths[0]= cls.getPackage().getName();
        return paths;
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
