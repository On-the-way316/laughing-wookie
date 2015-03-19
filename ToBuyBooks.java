# laughing-wookie
import java.util.Scanner;

public class ToBuyBooks {
	public static double getLowestPrice(int x1,int x2,int x3,int x4,int x5)
	 {
		double price=0.0;
	    double threshold=100000000.0;
	    double []array={x1,x2,x3,x4,x5};
	    bubble_sort(array,5);//将卷1到卷5购买的本数按从小大大的顺序排序
	    x1=(int)array[0];
	    x2=(int)array[1];
	    x3=(int)array[2];
	    x4=(int)array[3];
	    x5=(int)array[4];
		if(x1>0){
			price=min(5*8*0.75+getLowestPrice(x1-1,x2-1,x3-1,x4-1,x5-1),//从大的本数往下减，否则小的本数减到0后可能无法充分参与下次的折扣
					  4*8*0.8+getLowestPrice(x1,x2-1,x3-1,x4-1,x5-1),
					  3*8*0.9+getLowestPrice(x1,x2,x3-1,x4-1,x5-1),
					  2*8*0.95+getLowestPrice(x1,x2,x3,x4-1,x5-1),
					  8+getLowestPrice(x1,x2,x3,x4,x5-1));
		}else if(x1==0&&x2>0){
			price=min(4*8*0.8+getLowestPrice(0,x2-1,x3-1,x4-1,x5-1),
					  3*8*0.9+getLowestPrice(0,x2,x3-1,x4-1,x5-1),
					  2*8*0.95+getLowestPrice(0,x2,x3,x4-1,x5-1),
					  8+getLowestPrice(0,x2,x3,x4,x5-1), threshold);
		}else if(x1==0&&x2==0&&x3>0){
			price=min(3*8*0.9+getLowestPrice(0,0,x3-1,x4-1,x5-1),
					  2*8*0.95+getLowestPrice(0,0,x3,x4-1,x5-1),
					  8+getLowestPrice(0,0,x3,x4,x5-1), threshold, threshold);
		}else if(x1==0&&x2==0&&x3==0&&x4>0){
			price=min(2*8*0.95+getLowestPrice(0,0,0,x4-1,x5-1),
					  8+getLowestPrice(0,0,0,x4,x5-1), threshold, threshold, threshold);
		}else if(x1==0&&x2==0&&x3==0&&x4==0&&x5>0){
			price= 8+getLowestPrice(0,0,0,0,x5-1);
		}else if(x1==0&&x2==0&&x3==0&&x4==0&&x5==0){//递归出口
			price=0.0;
		}
		return price;
	 }

	public static void  bubble_sort(double []array,int n){//冒泡排序
		for(int i=n-1;i>0;i--){
			for(int j=0;j<i;j++){
				if(array[j]>array[j+1]){
					double temp=0;
					temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}
	}
	
	public static double min(double x1,double x2,double x3,double x4,double x5){//最小值函数
		double []array={x1,x2,x3,x4,x5};
		bubble_sort(array,5);
		return array[0];
	}
	@SuppressWarnings("resource")
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String pInStr=sc.nextLine();
		String []strArray=pInStr.split("\\s");
		int []intArray=new int[5];
		for(int j=0;j<5;j++){
			intArray[j]=Integer.parseInt(strArray[j]);
		}
		Double result=getLowestPrice(intArray[0],intArray[1],intArray[2],intArray[3],intArray[4]);
		System.out.println(result);
	} 
}
