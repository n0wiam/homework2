package org.example;

import com.sun.jdi.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        Calculate calculate=new Calculate();
        Generate generate=new Generate();
//        int n=10;
//        int r=10;
//        IOUtil.write(generate.big(r,n),"./Exercises.txt");
//        List<String> list = IOUtil.read("E:\\tem\\res.txt");
//        System.out.println(list);
        if(args.length<4)//参数个数判断
        {
            System.out.println("参数不足!");
        }
        if(args[0].equals("-r")&&args[2].equals("-n"))
        {
            int n=Integer.parseInt(args[3]);
            int r=Integer.parseInt(args[1]);
            List<String> list=null;

            if(r<=300) list=generate.small(r,n);//对不同的数据范围使用不同的构造方法使得时间不会太久
            else list=generate.big(r,n);

            List<String> ans=new ArrayList<>();
            for(int i=0;i< list.size();i++)
            {
                String result = calculate.getResult(list.get(i).split("\\.")[1]);//对于生成的算式计算答案
                ans.add((i+1)+"."+result);
            }

            IOUtil.write(list,"./Exercises.txt");//写入文件
            IOUtil.write(ans,"./Answer.txt");

        }
        else if(args[2].equals("-r")&&args[0].equals("-n"))//与上相同，参数位置不同
        {
            int n=Integer.parseInt(args[1]);
            int r=Integer.parseInt(args[3]);
            List<String> list=null;
            if(r<=300) list=generate.small(r,n);
            else list=generate.big(r,n);

            List<String> ans=new ArrayList<>();
            for(int i=0;i< list.size();i++)
            {
                String result = calculate.getResult(list.get(i).split("\\.")[1]);
                ans.add((i+1)+result);
            }

            IOUtil.write(list,"./Exercises.txt");
            IOUtil.write(ans,"./Answer.txt");
        }
        else if(args[0].equals("-e")&&args[2].equals("-a"))
        {
            String a=args[3];
            String e=args[1];
            Check check=new Check();
            IOUtil.write(check.getResult(e,a),"./Grade.txt");//将计算结果写入文件
        }
        else if(args[2].equals("-e")&&args[0].equals("-a"))
        {
            String a=args[1];
            String e=args[3];
            Check check=new Check();
            IOUtil.write(check.getResult(e,a),"./Grade.txt");
        }
        else{
            System.out.println("参数出错!");
        }
    }
}
