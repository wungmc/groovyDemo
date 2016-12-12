package com.wung.groovy

/**
 * @author wung
 *
 */
class Test{

    static void main(def args){
        //-------------------字符串
        def a = 'haha';
        println a;

        def s = "hello" +
                " \"world\"" + // \转义字符
                " ,groovy";
        println s;

        def s1 = """hello
			world
			,groovy!""";
        println s1; // 三个“之间的字符，会保留格式，包括换行、tab等

        s1 = 100 // 变量的类型会自动转变
        println s1.class; // output class java.lang.Integer

        //--------------------循环
        repeat(s);

        for (i in 0..4) {
            println "this is ${i}" //"之间的为Gstring, 输出：this is 0 等
            println 'that is ${i}' // '之间的为java.lang.String, 原样输出：that is ${i}
        }

        //--------------------范围
        //如上面的0..4 表示：0,1,2,3,4 0..<4 表示：0,1,2,3 a..c 表示：a,b,c
        //主要用于循环

        //--------------------默认参数值
        repeat2("haha", 4) // output haha: 4
        repeat2("hehe") // output hehe: 3

        //--------------------集合
        def col = [2,4,6,8,10];
        println col;
        //添加元素的方法
        col = col + 12;
        col.add(14);
        col<<16;
        col[col.size()] = 18
        println col;
        //支持集合运算，删除元素
        col -= 16
        col -= col[0..2] //删除前三个元素
        println col
        //支持负索引，-1表示倒数第一个，以此类推
        println col[-1];

        //--------------------map
        def m = ['name':'jack','age':29];
        println m;
        //添加元素
        m += ['id':01];
        m.put('weight', 65);
        m.height = 180;
        println m;
        println m.age.class
        println m.height

        //--------------------闭包
        //进行集合的迭代
        m.each({key, value -> println "$key:$value"})
        m.each{println it} //it是一个关键字，代表map集合的每个元素。each的()可以省略
        m.each({println it.getKey() + "-->" + it.getValue()})

        //闭包可以单独定义
        def say={name -> println "hello, $name"}
        say("jones") //output hello, jones
        say.call("jack") //output hello, jack

        //---------------------?运算符
        def hh = 'test'
        println hh?.class //?在这里是一个条件运算符，如果?前面的对象非null，执行后面的方法，否则什么也不做。

        //---------------------可变参数
        println sum(1)
        println sum(1, 2)
        println sum(1, 2, 3)

        //---------------------枚举
        def today = WeekDay.SATURDAY
        switch (today) {
            case [WeekDay.SUNDAY, WeekDay.SATURDAY] :
                println 'weekends are cool'
                break;
            case WeekDay.MONDAY..WeekDay.FRIDAY :
                println 'work day are boring'
                break
            default :
                println 'are you sure this is a valid day?'
                break;
        }

        //----------------------Elvis操作符
        //就是三目运算符，在groovy中，null可以转化为false布尔值，所以可以这样写
        def s2 = 'string'
        println s2 ? s2 : 'null' //output string
        //基于“不重复”原则，又可以写成这样：
        println s2 ?: 'null' //?和：之间不能有空格

        //----------------------动态性
        def msg = 'hello!'
        println msg.metaClass
        String.metaClass.up = {  delegate.toUpperCase() } //动态增加方法
        println msg.up() // output HELLO!
        //便利对象的所有的方法和属性
//		msg.metaClass.methods.each({ println it.name })
//		msg.metaClass.properties.each { println it.name }
        //我们可以通过元类判断有没有一个叫up的方法，然后再调用它：
        if (msg.metaClass.respondsTo(msg, 'up')) {
            println msg.toUpperCase()
        }
        //当然，也可以推断它有没有一个叫bytes的属性：
        if (msg.metaClass.hasProperty(msg, 'bytes')) {
            println msg.bytes.encodeBase64()
        }

    }

    //for循环
    def static repeat(val) {
        for (def i = 0; i < 4; i++) {
            println val;
        }
        // 或者写成下面也可以
//		for (j in 0..3) {
//			println val;
//		}
        // 或者下面
//		for (j in 0..<4) {
//			println val
//		}
    }

    //参数repeat的默认值为3，如果调用该方法时不传入repeat参数，则默认使用3
    def static repeat2(val, repeat = 3) {
        println "${val}: ${repeat}"
    }

    //可变参数
    static int sum(int... var) {
        def total = 0
        for (i in var)
            total += i
        return total
    }
}

