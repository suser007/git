package com.susur.concurrentset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/***
 * List Set Map : 集合遍历过程中不允许 增、删、改，会报出如下异常，所以引出了并发集合
 * Exception in thread "main" java.util.ConcurrentModificationException
 * 	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
 * 	at java.util.ArrayList$Itr.next(ArrayList.java:859)
 * 	at com.susur.concurrentset.UserDemo.main(UserDemo.java:17)
 */
public class UserDemo {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            users.add(new User("User"+i,i));
        }

        //例子：遍历过程中不允许更新操作
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User user = it.next();
            if("User6".equals(user.getName())){
                users.remove(user);
            }
        }
//        users.remove(8);
        System.out.println(Arrays.toString(users.toArray()));
    }
    /***
     * Exception in thread "main" java.util.ConcurrentModificationException
     *  * 	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
     *  * 	at java.util.ArrayList$Itr.next(ArrayList.java:859)
     *  * 	at com.susur.concurrentset.UserDemo.main(UserDemo.java:17)
     */
}
