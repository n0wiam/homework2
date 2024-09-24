package org.example;

public class Calculate {

    public String getResult(String str)
    {
        double[] num=new double[100];//数字栈
        char[] ops=new char[100];//符号栈
        int nt=0;
        int ot=0;
        //采用栈方式来将字符串处理成代码可处理的形式
        //符号优先即为（+-*/）
        //创建两个栈，数字栈和符号栈
        //当遇到栈顶优先级高于当前的符号时，将数字栈的头两个元素取出，进行计算，并更新符号栈
        //对于除法，此函数将使用double记录结果
        for(int i=0;i<str.length();i++) {
            char ch = str.charAt(i);
            if(ch=='=') break;
            if (ch >= '0' && ch <= '9') {//遇到数字一个个取出来放到数字栈中
                double tem = (ch - '0');
                int j = i + 1;
                while (j < str.length() && str.charAt(j) >= '0' && str.charAt(j) <= '9') {
                    ch = str.charAt(j);
                    if (ch >= '0' && ch <= '9') tem = tem * 10 + (ch - '0');
                    j++;
                }
                num[nt++] = tem;
                //System.out.println(tem);
                i = j - 1;
            } else if (ch == '(') ops[ot++] = '(';
            else if (ch == '*') {
                if (ot == 0 || ops[ot - 1] == '+' || ops[ot - 1] == '-' || ops[ot - 1] == '(') ops[ot++] = '*';
                else if (ops[ot - 1] == '*') {
                    double a = num[nt - 2];
                    double b = num[nt - 1];
                    nt -= 2;
                    num[nt++] = a * b;
                }
                else if (ops[ot - 1] == '/') {
                    double a = num[nt - 2];
                    double b = num[nt - 1];
                    nt -= 2;
                    num[nt++] = a / b;
                    ops[ot-1]='*';
                }
            } else if (ch == '+' || ch == '-') {
                if (ot == 0 || ops[ot - 1] == '(') ops[ot++] = ch;
                else if (ops[ot - 1] == '+') {
                    double a = num[nt - 2];
                    double b = num[nt - 1];
                    nt -= 2;
                    num[nt++] = a + b;
                    ops[ot - 1] = ch;
                } else if (ops[ot - 1] == '-') {
                    double a = num[nt - 2];
                    double b = num[nt - 1];
                    nt -= 2;
                    num[nt++] = a - b;
                    ops[ot - 1] = ch;
                } else if (ops[ot - 1] == '*') {
                    double a = num[nt - 2];
                    double b = num[nt - 1];
                    nt -= 2;
                    num[nt++] = a * b;
                    ops[ot - 1] = ch;
                }
                else if (ops[ot - 1] == '/') {
                    double a = num[nt - 2];
                    double b = num[nt - 1];
                    nt -= 2;
                    num[nt++] = a / b;
                    ops[ot - 1] = ch;
                }
            }else if (ch=='/') {
                if (ot == 0 || ops[ot - 1] == '+' || ops[ot - 1] == '-' || ops[ot - 1] == '(') ops[ot++] = '/';
                else if (ops[ot - 1] == '*') {
                    double a = num[nt - 2];
                    double b = num[nt - 1];
                    nt -= 2;
                    num[nt++] = a * b;
                    ops[ot - 1] = '/';
                }else if (ops[ot - 1] == '/') {
                    double a = num[nt - 2];
                    double b = num[nt - 1];
                    nt -= 2;
                    num[nt++] = a / b;
                    ops[ot - 1] = '/';
                }
            }else if (ch == ')') {
                while (ot > 0 && ops[ot - 1] != '(') {
                    double a = num[nt - 2];
                    double b = num[nt - 1];
                    nt -= 2;
                    if (ops[ot - 1] == '*') num[nt++] = a * b;
                    else if (ops[ot - 1] == '+') num[nt++] = a + b;
                    else if (ops[ot - 1] == '-') num[nt++] = a - b;
                    else if (ops[ot - 1] == '/') num[nt++] = a / b;
                    ot--;
                }
                if (ot>0&&ops[ot - 1] == '(') ot--;
            }
        }

        //字符串末尾,将栈清空
        while (nt > 1) {
            double a = num[nt - 2];
            double b = num[nt - 1];
            nt -= 2;
            if (ops[ot - 1] == '*') num[nt++] = a * b;
            else if (ops[ot - 1] == '+') num[nt++] = a + b;
            else if (ops[ot - 1] == '-') num[nt++] = a - b;
            else if (ops[ot - 1] == '/') num[nt++] = a / b;
            ot--;
        }
        //for(int i=0;i<nt;i++) System.out.println(num[i]);
        //System.out.println(num[0]);

        //由于除法得到的是double类型，这里采用枚举进行格式化
        int resa=100,resb=100;
        double smin=10000;
        if(num[0]==0) return "0";
        for(int i=1;i<10000;i++)
        {
            int a= (int) (num[0]*i);
            double s=Math.abs((1.0*a/i)-num[0]);
            if(s<smin) {
                resa = a;
                resb = i;
                smin=s;
            }
        }

        //对不同情况下的答案处理
        if(resb==1) return ""+resa;
        else if(resa<resb) return resa+"/"+resb;
        else {
            if(resa%resb==0) return ""+resa/resb;
            return resa/resb+"'"+resa%resb+"/"+resb;
        }
    }
}
