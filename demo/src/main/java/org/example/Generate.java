package org.example;

import java.util.ArrayList;
import java.util.List;

public class Generate {
    char[] ops={'+','-','*','/'};
    public List<String> big(int smax, int sz){//大范围的数据可知用一个符号即可实现10000个算式
        List<String> res=new ArrayList<>();
        int count=1;
        for(int i=0;i<4;i++)
        {
            for(int a=1;a<smax;a+=2)
            {
                for(int b=2;b<smax&&count<=sz;b+=2)
                {
                    if(i==0&&a+b>smax) continue;
                    else if(i==1&&a-b<0) continue;
                    else if(i==2&&a*b>smax) continue;
                    String str=""+count+'.'+a+ops[i]+b;
                    //System.out.println(str);
                    res.add(str);
                    count++;
                    if(count>sz) return res;
                }
            }
        }
        return res;
    }

    public List<String> small(int smax,int sz){//小范围数据用两个符号
        List<String> res=new ArrayList<>();
        int count =1;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                for(int a=1;a<smax;a+=2)
                {
                    for(int b=2;b<smax;b+=2)
                    {
                        for(int c=3;c<smax;c+=2)
                        {
                            //先对产生负数和超出范围的情况判断
                            if(i==0&&j==0&&a+b+c>smax) continue;
                            else if(i==0&&j==1&&(a+b-c>smax||a+b-c<0)) continue;
                            else if(i==0&&j==2&&a+b*c>smax) continue;
                            else if(i==0&&j==3&&a+b/c>smax) continue;
                            else if(i==1&&j==0&&(a-b+c>smax||a-b<0)) continue;
                            else if(i==1&&j==1&&(a-b-c<0||a-b<0)) continue;
                            else if(i==1&&j==2&&a-b*c<0) continue;
                            else if(i==1&&j==3&&a*c-b<0) continue;
                            else if(i==2&&j==0&&a*b+c>smax) continue;
                            else if(i==2&&j==1&&(a*b-c>smax||a*b-c<0)) continue;
                            else if(i==2&&j==2&&a*b*c>smax) continue;
                            else if(i==2&&j==3&&a*b/c>smax) continue;
                            else if(i==3&&j==0&&a/b+c>smax) continue;
                            else if(i==3&&j==1&&(a/b-c>smax||a/b-c<0)) continue;
                            else if(i==3&&j==2&&a/b*c>smax) continue;
                            else if(i==3&&j==3&&a/b/c>smax) continue;
                            String str=""+count+'.'+a+ops[i]+b+ops[j]+c;
                            //System.out.println(str);
                            res.add(str);
                            count++;
                            if(count>sz) return res;
                        }
                    }
                }
            }
        }
        return res;
    }
}
