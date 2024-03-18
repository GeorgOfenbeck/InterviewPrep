package datastructures;

import java.util.HashMap;
import java.util.Objects;


public class JavaHashNonSense {

    public void doStuff(HashMap<Content, String> map){
        map.put(new Content(20),"hello");
        map.put(new Content(20),"world");
        String value = map.get(new Content(30));

        System.out.println(value);
    }


    class Content{
        int id;
        Content(int pid){
            id = pid;
        }

        @Override
        public boolean equals(Object o){

            if (o == this) return true;

            if (!(o instanceof Content)){
                return false;
            }

            Content other = (Content) o;

            return Objects.equals(other.id,this.id);
        }

        @Override

        public int hashCode() {
            return this.id;
        }
    }

    Content getNewContent(int pid) {
            return new Content(pid);
    }

    HashMap<Content,String> hashMap = new HashMap<>();


    public static void main(String[] args) {
        JavaHashNonSense nonSense = new JavaHashNonSense();

        Content content = nonSense.getNewContent(10);
        nonSense.hashMap.put(content,"hello");

        Content content2 = nonSense.getNewContent(10);
        System.out.println(nonSense.hashMap.containsKey(content2));

        nonSense.doStuff(nonSense.hashMap);

    }

}
