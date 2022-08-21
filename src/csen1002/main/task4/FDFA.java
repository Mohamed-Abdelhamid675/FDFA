package csen1002.main.task4;

import java.util.Stack;
import java.util.ArrayList;
public class FDFA {
    String description;
    Stack <Integer> s = new Stack<>();
    ArrayList<String> finals=new ArrayList<String>();
    ArrayList<Integer> goals=new ArrayList<Integer>();
    String output=new String();



    public FDFA(String description) {
        this.description=description;
    }


    public String run(String input) {
String[] hashtransations=description.split("#");
String[] goalnodes=hashtransations[1].split(",");
for(int i=0;i<goalnodes.length;i++){
    int x=Integer.parseInt(goalnodes[i]);
    goals.add(x);
}
String[] transations=hashtransations[0].split(";");
int right=0;
int left=0;
s.push(0);


for (int i=0;i<input.length();i++){
    for(int j=0;j<transations.length;j++){
        String [] temp=transations[j].split(",");
        int x=Integer.parseInt(temp[0]);
        int y=Integer.parseInt(temp[1]);
        int z=Integer.parseInt(temp[2]);

        if(x == s.peek()){
            if(input.charAt(i)=='0'){
                s.push(y);
                left++;
                break;
            }
            else {
                s.push(z);
                left++;
                break;
            }
        }
    }
}
left--;

for(int i=0;i<transations.length;i++){
    System.out.println(transations[i]);
}

String temp=input;


while(left!=temp.length() &&s.size()!=0){


    if(goals.contains(s.peek())==false){
        s.pop();
        left--;
        System.out.println(s);

    }


    else {
        String newtext=temp.substring(right,left+1);//the accepted string
        finals.add(newtext);
        temp=temp.substring(left+1,temp.length());
        left=0;
        right=0;
        s=new Stack<>();
        s.add(0);
        for(int i=0;i<temp.length();i++){
            for(int j=0;j<transations.length;j++){
                String [] te=transations[j].split(",");
                int x=Integer.parseInt(te[0]);
                int y=Integer.parseInt(te[1]);
                int z=Integer.parseInt(te[2]);

                if(x == s.peek()){
                    if(temp.charAt(i)=='0'){
                        s.push(y);
                        left++;
                        break;
                    }
                    else {
                        s.push(z);
                        left++;
                        break;
                    }
                }


            }


        }
        left--;
        finals.add(temp);
    }
}

if(finals.size()==0){
    s=new Stack<>();
    s.push(0);
    for (int i=0;i<input.length();i++){
        for(int j=0;j<transations.length;j++){
            String [] temp1=transations[j].split(",");
            int x=Integer.parseInt(temp1[0]);
            int y=Integer.parseInt(temp1[1]);
            int z=Integer.parseInt(temp1[2]);

            if(x == s.peek()){
                if(input.charAt(i)=='0'){
                    s.push(y);
                    break;
                }
                else {
                    s.push(z);
                    break;
                }
            }
        }
    }
        for(int i=0;i<transations.length;i++){
            String [] temp2=transations[i].split(",");
            int x=Integer.parseInt(temp2[0]);
            if(x==s.peek()){
                output=input+","+temp2[3]+";";
            }
        }

}
else {
    for(int k=0;k<finals.size();k++){
        if(finals.get(k).length()!=0) {


            String temp4 = finals.get(k);
            s = new Stack<>();
            s.push(0);
            for (int i = 0; i < temp4.length(); i++) {
                for (int j = 0; j < transations.length; j++) {
                    String[] temp1 = transations[j].split(",");
                    int x = Integer.parseInt(temp1[0]);
                    int y = Integer.parseInt(temp1[1]);
                    int z = Integer.parseInt(temp1[2]);

                    if (x == s.peek()) {
                        if (temp4.charAt(i) == '0') {
                            s.push(y);
                            break;
                        } else {
                            s.push(z);
                            break;
                        }
                    }
                }
            }
            System.out.println("stack:" + s);
            for (int i = 0; i < transations.length; i++) {
                String[] temp2 = transations[i].split(",");
                int x = Integer.parseInt(temp2[0]);
                if (x == s.peek()) {
                    output = output + temp4 + "," + temp2[3] + ";";
                }
            }
        }
    }
}
System.out.println(output);
        return output;
    }
    public static void main(String[] args) {
FDFA f= new FDFA("0,0,1,N;1,2,1,A;2,3,0,B;3,3,0,O#1,2");
f.run("011000");
    }
}

