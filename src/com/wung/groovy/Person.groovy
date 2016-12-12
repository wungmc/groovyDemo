package com.wung.groovy

/**
 * javabean定义，不需要构造方法和getter、setter方法
 * @author wung
 *
 */
class Person{

    def name
    def age

    String toString() {
        "name=$name, age=$age"
        //不需要return, Groovy 默认返回方法的最后一行的值
    }

    static void main(def args){
        def p1 = new Person()
        p1.name = 'gg'
        p1.age = 20
        println p1

        def p2 = new Person(['name':'kk', 'age':22])
        println p2
    }

}
