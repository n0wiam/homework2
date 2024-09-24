package org.example;

import java.util.ArrayList;
import java.util.List;

public class Check {
    public List<String> getResult(String e,String a){

        Calculate calculate=new Calculate();

        int correct=0;
        int error=0;
        List<Integer> correctList=new ArrayList<>();//正确题号
        List<Integer> errorList=new ArrayList<>();//错误题号
        List<String> que=IOUtil.read(e);//问题列表
        List<String> ans=IOUtil.read(a);//答案列表
        List<String> que_res=new ArrayList<>();//问题计算结果
//        String[] tem=que.get(0).split("\\.");
        que.forEach(ask->{
            String[] tem=ask.split("\\.");
            que_res.add(calculate.getResult(tem[1]));//将问题交由计算类进行计算
        });
        for(int i=0;i<que_res.size()&&i< ans.size();i++)//对正确错误进行统计
        {
            if(que_res.get(i).equals(ans.get(i).split("\\.")[1]))
            {
                correct++;
                correctList.add(i+1);
            }
            else{
                error++;
                errorList.add(i+1);
                //System.out.println(que_res.get(i));
            }
        }
        StringBuilder sc= new StringBuilder("Correct:" + correct + "(");//构造统计结果输出
        for(int i=0;i<correctList.size();i++)
        {
            if(i!=0) sc.append(",");
            sc.append(correctList.get(i));
        }
        sc.append(")");

        StringBuilder se= new StringBuilder("Wrong:" + error + "(");
        for(int i=0;i<errorList.size();i++)
        {
            if(i!=0) se.append(",");
            se.append(errorList.get(i));
        }
        se.append(")");
        List<String> grade=new ArrayList<>();//记录结果
        grade.add(sc.toString());
        grade.add(se.toString());
        return  grade;
    }
}
