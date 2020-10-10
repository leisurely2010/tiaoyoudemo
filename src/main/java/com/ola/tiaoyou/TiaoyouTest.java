/**
 * 
 */
package com.ola.tiaoyou;

/**
 * @类功能说明：    
 * @作者：Administrator  
 * @创建时间：2020年9月28日 下午12:03:50  
 * @版本：V1.0  
 */
public class TiaoyouTest {
	public static void jvmdetail(){
		//查询cpu个数
		System.out.println("cpu:"+Runtime.getRuntime().availableProcessors());
		//java虚拟机试图使用的最大内存-Xmx(默认物理内存1/4)
		System.out.println("java虚拟机试图使用的最大内存:"+Runtime.getRuntime().maxMemory()/1024/1024+"MB");
		//java虚拟机初始内存总量-Xms(默认物理内存1/64)
		System.out.println("java虚拟机内存总量:"+Runtime.getRuntime().totalMemory()/1024/1024+"MB");
	}
	public static void main(String[] args) {
//		String str="atshagnguigu";
//		while (true){
//			str+=str+new Random().nextInt(99999)+new Random().nextInt(88888);
//		}
		byte[] b=new byte[40*1024*1024];
	}

}
