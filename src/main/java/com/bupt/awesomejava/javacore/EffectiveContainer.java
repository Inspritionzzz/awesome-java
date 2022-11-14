package com.bupt.awesomejava.javacore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class EffectiveContainer {

    private final static Logger logger = LoggerFactory.getLogger(EffectiveContainer.class);
    public static void main(String[] args) {

        // 1.1
        myArrayList();
        // 1.2
        // myLinkList();
        // 1.3
        // mySet();
        // 1.4
        // myQueue();
        // 1.5
        // myMap();


        // ele-1: lambda表达式使用
        // lambdaSample();
    }

    /**
     * 1.1 ArrayList
     */
    public static void myArrayList() {
        List<String> stringList = new ArrayList<>();
        /** 1. 使用 isEmpty() 判断集合是否为空，而不是 size()==0 */
        stringList.isEmpty();

        /**
         * 2. 不要在 foreach 循环里进行元素的 remove/add 操作。remove 元素请使用 Iterator 方式，如果并发操作，需要对 Iterator 对象加锁。
         * 通过反编译你会发现 foreach 语法底层其实还是依赖 Iterator 。
         * 不过，remove/add 操作直接调用的是集合自己的方法，而不是 Iterator 的 remove/add方法。
         * 这就导致 Iterator 莫名其妙地发现自己有元素被 remove/add ，
         * 然后，它就会抛出一个 ConcurrentModificationException 来提示用户发生了并发修改异常。这就是单线程状态下产生的 fail-fast 机制。
         * 注：fail-fast 机制：多个线程对 fail-fast 集合进行修改的时候，可能会抛出ConcurrentModificationException。即使是单线程下也有可能会出现这种情况
         * 2.1 可以使用 Collection#removeIf() 方法删除满足条件的元素（jdk8 以上）
         * 2.2 使用普通的 for 循环
         * 2.3 使用 fail-safe 的集合类。java.util包下面的所有的集合类都是 fail-fast 的，而java.util.concurrent包下面的所有的类都是 fail-safe 的。
         */
        List<Integer> integerList = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            integerList.add(i);
        }
        integerList.removeIf(filter -> filter % 2 == 0);
        /**
         * 3. 集合去重：可以利用 Set 元素唯一的特性，可以快速对一个集合进行去重操作，避免使用 List 的 contains() 进行遍历去重或者判断包含操作。
         */
        List<Integer> integerList2 = Arrays.asList(1, 1, 2, 2, 3, 3);
        logger.info("integerList2去重前：" + integerList2);
        Set<Integer> integerSet = removeDuplicateBySet(integerList2);
        logger.info("integerList2去重后：" + new ArrayList<>(integerSet));
    }
    /**
     * 1.2 LinkList
     */
    public static void myLinkList() {
        List<String> stringList = new LinkedList<>();
        stringList.add("just a test");
    }

    /**
     * 1.3 Set
     */
    public static void mySet() {

    }

    /**
     * 1.4 Queue
     */
    public static void myQueue() {

    }

    /**
     * 1.5 Map
     */
    public static void myMap() {
        // 在使用 java.util.stream.Collectors 类的 toMap() 方法转为 Map 集合时，一定要注意当 value 为 null 时会抛 NPE 异常
        List<User> userList = new LinkedList<>();
        userList.add(new User("1", "jason"));
        userList.add(new User("2", "jack"));
        // userList.add(new User("3", null));  // 空指针异常
        System.out.println(userList.stream().collect(Collectors.toMap(User::getRoleId, User::getRoleName)));
    }

    /**
     * ele-1: lambda表达式使用示例
     */
    public static void lambdaSample() {
        List<String> stringList = new ArrayList<String>() {
            { add("aa"); add("bb"); add("cc");}
        };
        List<String> stringList1 = Arrays.asList(new String[]{"aa", "bb", "cc"});

        for(String str : stringList){
            System.out.println(str);
        }

        stringList.forEach(item -> {
            System.out.println(item);
        });
        stringList.forEach(System.out::println);
        stringList.stream().filter(s -> s.equals("aa")).forEach(System.out::println);
    }

    /**
     * ele-2.1: 双层遍历性能优化
     * @param userList
     * @param roleList
     */
    public static void loopOptimization1(List<User> userList, List<Role> roleList) {
        for(User user: userList) {
            for(Role role: roleList) {
                if(user.getRoleId().equals(role.getId())) {
                    user.setRoleName(role.getName());
                }
            }
        }
    }

    /**
     * ele-2.2: 双层遍历性能优化
     * @param userList
     * @param roleList
     */
    public static void loopOptimization2(List<User> userList, List<Role> roleList) {
        Map<String, List<Role>> roleMap = roleList.stream().collect(Collectors.groupingBy(Role::getId));
        for (User user : userList) {
            List<Role> roles = roleMap.get(user.getRoleId());
            if(CollectionUtils.isEmpty(roles)) {
                continue;
            } else {
                user.setRoleName(roles.get(0).getName());
            }
        }
    }

    /**
     * Set 去重代码示例
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Set<T> removeDuplicateBySet(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return new HashSet<>();
        }
        return new HashSet<>(data);
    }

    /**
     * List 去重代码示例
     * @param data
     * @param <T>
     * @return
     */
    public static <T> List<T> removeDuplicateByList(List<T> data) {

        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>();

        }
        List<T> result = new ArrayList<>(data.size());
        for (T current : data) {
            if (!result.contains(current)) {
                result.add(current);
            }
        }
        return result;
    }


}



class User {
    private String roleId;
    private String roleName;

    public User(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

class Role {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
