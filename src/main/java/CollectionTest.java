import com.google.common.collect.*;

import java.util.*;

public class CollectionTest {


    public static void main(String[] args){
        System.out.println("start");
        ImmutableSet<String> foobar = ImmutableSet.of("foo", "bar", "baz");


        ImmutableList<Integer> intlist = ImmutableList.of(1,2,3,4);
        ImmutableList<Integer> intlist2 = ImmutableList.of();
        System.out.println(intlist2.size());

        ImmutableMap<Integer,Float> map = ImmutableMap.of(1 ,3.0f);

        


    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();


      /*  for (int i = 0; i < nums.length; i++)
            map.put(nums[i],i);
*/

        Iterator<Integer> it = Arrays.stream(nums).iterator();

        int i = 0;
        boolean found = false;
        Integer a = -1;
        Integer b = -1;
        while(it.hasNext()){
            Integer value = it.next();
            map.put(value,i);
            i++;
            if (map.containsKey(target - value))
            {
                a = value;
                b = map.get(target-value);
            }
        }
        int [] ret = new int[2];
        ret[0] = a.intValue();
        ret[1] = b.intValue();

        String blub = "asdaf";
        Set<Character> blubx = null;
        blubx.
        return ret;


    }
}
