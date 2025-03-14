import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        LinkedList<CompressedForm> aList = new LinkedList<>();
        LinkedList<CompressedForm> bList = new LinkedList<>();
        String a[] = in.nextLine().split(" ");
        String b[] = in.nextLine().split(" ");
        in.close();
        
        for(int i = 0; i < a.length; i+=2){
            aList.add(new CompressedForm(Integer.parseInt(a[i]), Long.parseLong(a[i+1])));
        }

        for (int i = 0; i < b.length; i+=2){
            bList.add(new CompressedForm(Integer.parseInt(b[i]), Long.parseLong(b[i+1])));
        }
        System.out.println(dotProduct(aList, bList));
    }

    public static long dotProduct(LinkedList<CompressedForm> aList, LinkedList<CompressedForm> blist){
        long result = 0;
        while(aList.size() > 0 && blist.size() > 0){
            var a = aList.peek();
            var b = blist.peek();
            if(a.count == b.count){
                result += (a.value * b.value) * a.count;
                aList.removeFirst();
                blist.removeFirst();
            }else if(a.count > b.count){
                result += (a.value * b.value) * b.count;
                a.count -= b.count;
                blist.removeFirst();
            }else{
                result += (a.value * b.value) * a.count;
                b.count -= a.count;
                aList.removeFirst();
            }
        }
        return result;
    }
}

class CompressedForm{
    long value;
    int count;
    CompressedForm(int count, long value){
        this.value = value;
        this.count = count;
    }

    @Override
    public String toString(){
        return value + " " + count;
    }
}
