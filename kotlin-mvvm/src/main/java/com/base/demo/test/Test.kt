package com.base.demo.test

import android.util.Log
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 * 描述: Kotlin基本语法练习
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-09 14:19
 */
class Test {
    var str: String = ""// 成员变量必须赋初始值
    /**
     *
     * 测试局部变量
     */
    fun testVar() {
        str = "abcd"
        Log.d("fanhy", "str = " + str)

        /**
         * 自动推断类型：定义变量时必须直接赋初始值
         */
        var a = 1       // 根据赋值自动推断类型
        a = 2
        //a = ""        // 定义的时候根据赋值推断出a为Int类型，所以不能赋值String类型
        a = 1.1.toInt() // 强制类型转换

        var b = ""
        b = "Hello"
        //b = 1         // 定义的时候根据赋值推断出a为String类型，所以不能赋值Int类型

        var c = 1.2
        c = 1.0
        //c = 1         // 不能自动类型转换

        //var d         // 未指定类型的变量必须赋初始值

        Log.d("fanhy", "testVar：a = " + a)
        Log.d("fanhy", "testVar：b = " + b)
        Log.d("fanhy", "testVar：c = " + c)

        /**
         *
         * 指定类型：定义变量时不用赋初始值，但必须指定类型
         */
        var name: String
    }

    /**
     *
     * 测试常量
     */
    fun testVal() {
        val a = 10
        //a = 12            // 常量不能修改值
        Log.d("fanhy", "testVal：a = " + a)
    }

    /**
     *
     * 测试字符串引用
     */
    fun testStrRefrence() {
        var s1 = "世界"
        val s2 = "world"

        Log.d("fanhy", "testStrRefrence：你好, $s1")       // 类似于下面
        Log.d("fanhy", "testStrRefrence：你好, " + s1)

        Log.d("fanhy", "testStrRefrence：Hello, $s2")       // 类似于下面
        Log.d("fanhy", "testStrRefrence：Hello, " + s2)
    }

    /**
     *
     * 测试变量为null的处理方式
     */
    fun testNull() {
        var s1: String = "Hello"            // 不能为null
        var s2: String? = null              // 可以为null， 可调用  .?  进行安全调用
        var s3: String? = null              // 可以为null， 可调用  .?  进行安全调用
        Log.d("fanhy", "testNull：s1的长度：" + s1.length)
        Log.d("fanhy", "testNull：s2的长度：" + s2?.length)
        s2 = "你好"
        Log.d("fanhy", "testNull：s2的长度：" + s2.length)
        s3 = "Test"
        Log.d("fanhy", "testNull：s3的长度：" + s3.length)// 非空断言，不为null则执行
        s3 = null
        try {
            Log.d("fanhy", "testNull：s3的长度：" + s3!!.length)// 非空断言，为null则抛KotlinNullPointerException异常
        } catch (k: KotlinNullPointerException) {
            Log.d("fanhy", "testNull：" + k)// 非空断言，为null则抛KotlinNullPointerException异常
        }
    }

    /**
     *
     * 测试可变参数
     */
    fun testChangeParam(vararg args: String) {
        for (item in args) {
            Log.d("fanhy", "testChangeParam：$item")
        }
    }

    /**
     *
     * 测试函数参数
     */
    fun testParams(name: String, age: Int, isMarray: Boolean) {
        Log.d("fanhy", "testParams：姓名：$name\t年龄：$age\t婚否：$isMarray")
    }

    /**
     *
     * 测试函数参数
     */
    fun testParams(map: Map<String, String>) {
        val entries = map.entries
        for (entry in entries) {
            Log.d("fanhy", "testParams：" + entry.key + " = " + entry.value)
        }
    }

    /**
     *
     * 测试函数返回值
     */
    fun testReturn(source: String): String {
        return source.toUpperCase()
    }

    /**
     *
     * 测试函数返回值：运行空参null和返回null
     */
    fun testReturn2(source: String?): String? {
        return source?.toUpperCase()
    }

    /**
     *
     * 测试循环
     */
    fun testForArrange(start: Int, end: Int) {// 左闭右开
        for (i in start until end) {
            Log.d("fanhy", "testForArrange：i = " + i)
        }
    }

    /**
     *
     * 测试循环
     */
    fun testForArrange(start: Int, end: Int, step: Int) {// 左闭右开
        for (i in start..end step step) {
            Log.d("fanhy", "testForArrange：step：i = " + i)
        }

        for (i in 20 downTo 5 step step) {
            if (i == 11) {
                continue
            }
            if (i == 5) {
                break
            }
            Log.d("fanhy", "testForArrange：downTo：i = " + i)
        }

        // while循环同Java
    }

    /**
     *
     * 测试循环
     */
    fun testForArrange() {// 左闭右开
        for (i in 10 until 20) {
            if (i % 2 == 0) {
                Log.d("fanhy", "testForArrange：偶数i = " + i)
            }
        }
    }


    /**
     *
     * 测试循环
     */
    fun testForArrange(isOutSide: Boolean) {
        var i = 10
        var j = 20
        // break标识
        out@ while (i > 0) {
            j = 20
            while (j > 0) {
                if (i == 5 && j == 8) {
                    if (isOutSide) {
                        break@out   // break到指定标识
                    } else {
                        break       // break当前循环
                    }
                }
                Log.d("fanhy", "i = $i, j = $j")
                j--
            }
            i--
        }
    }

    /**
     *
     * 测试直接返回参数的计算结果
     */
    fun sum(a: Int, b: Int) = a + b

    /**
     *
     * 测试   ?:  相当于if else
     */
    fun getNameLength(name: String?): Int {
        var length = name?.length ?: 0
        return length
    }

    /**
     *
     * 类型检测和转换
     */
    fun getStrLength(obj: Any): Int {
        var length = 0
        if (obj is String) {
            length = obj.length
        }
        return length
    }

    /**
     *
     * 测试基本数据类型：数值型6种，没有字符型
     */
    fun testBaseType() {
        var a: Byte
        var b: Short
        var c: Int
        var d: Long
        var e: Float
        var f: Double

        a = 10
        b = a.toShort()     // 小转大需要显式调用toXX方法
        a = b.toByte()      // 大转小同样需要显式调用toXX方法
        c = 10
        d = 10              // 数值型整数字面量可以赋值给Byte，Short，Int和Long
        e = 10F             // Float类型数值结尾必须是F，即使没有小数点
        e = 10.0F           // Float类型数值结尾必须是F，即使没有小数点
        f = 10.0            // Double类型数值必须有小数点且不能以F结尾
        f = e.toDouble()
        e = f.toFloat()
        e = a.toFloat()
        f = f + e           // 计算的时候把e从Float转成Double(自动转换)， 但是整数和小数之间不能自动转换
        f = (a + 1).toDouble()

        /*
        十进制：123
        长整型以大写的 L 结尾：123L
        16 进制以 0x 开头：0x0F
        2 进制以 0b 开头：0b00001011
        注意：8进制不支持
        Kotlin 同时也支持传统符号表示的浮点数值：

        Doubles 默认写法: 123.5, 123.5e10
        Floats 使用 f 或者 F 后缀：123.5f
        你可以使用下划线使数字常量更易读：

        val oneMillion = 1_000_000
        val creditCardNumber = 1234_5678_9012_3456L
        val socialSecurityNumber = 999_99_9999L
        val hexBytes = 0xFF_EC_DE_5E
        val bytes = 0b11010010_01101001_10010100_10010010

        在 Kotlin 中，三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小。
        */
        var a1: Int = 10
        var a2: Int = 10
        var a3: Int = a1
        Log.d("fanhy", "a1 == a2：" + (a1 == a2))
        Log.d("fanhy", "a1 === a2：" + (a1 === a2))
        Log.d("fanhy", "a1 == a3：" + (a1 == a3))
        Log.d("fanhy", "a1 === a3：" + (a1 === a3))
        a1 = 200
        a2 = 200
        Log.d("fanhy", "a1 == a2：" + (a1 == a2))
        Log.d("fanhy", "a1 === a2：" + (a1 === a2))
        Log.d("fanhy", "a1 == a3：" + (a1 == a3))
        Log.d("fanhy", "a1 === a3：" + (a1 === a3))

        var s1 = "Hello"
        var s2 = "Hello"
        var s3 = s1
        Log.d("fanhy", "s1 == s2：" + (s1 == s2))
        Log.d("fanhy", "s1 === s2：" + (s1 === s2))
        Log.d("fanhy", "s1 == s3：" + (s1 == s3))
        Log.d("fanhy", "s1 === s3：" + (s1 === s3))


        val aa: Int = 100
        val bb: Int = 128
        Log.d("fanhy", "aa === aa:" + (aa === aa)) // true，值相等，对象地址相等
        Log.d("fanhy", "bb === bb:" + (bb === bb)) // true，值相等，对象地址相等

        //经过了装箱，创建了两个不同的对象
        val boxedA: Int? = aa
        val anotherBoxedA: Int? = aa
        val boxedB: Int? = bb
        val anotherBoxedB: Int? = bb

        //虽然经过了装箱，但是值是相等的，都是100
        Log.d("fanhy", "boxedA === anotherBoxedA:" + (boxedA === anotherBoxedA)) //  true，值相等，128 之前对象地址一样
        Log.d("fanhy", "boxedA == anotherBoxedA:" + (boxedA == anotherBoxedA)) //  true，值相等，128 之前对象地址一样

        //虽然经过了装箱，但是值是相等的，都是100
        Log.d("fanhy", "boxedB === anotherBoxedB:" + (boxedB === anotherBoxedB)) //  true，值相等，128 之前对象地址一样
        Log.d("fanhy", "boxedB == anotherBoxedB:" + (boxedB == anotherBoxedB)) //  true，值相等，128 之前对象地址一样
    }

    /**
     *
     * if条件判断：可以把表达式的结果赋值给一个变量
     */
    fun getMax(a: Int, b: Int): Int {
        var c = if (a >= b) a else b
        return c
    }

    /**
     *
     * 区间判断
     */
    fun isInArrange(target: Int, from: Int, to: Int): Boolean {
        var isIn = target in from..to
        var isIn2 = if (target in from..to) true else false
        Log.d("fanhy", "isIn:" + isIn)
        Log.d("fanhy", "isIn2:" + isIn2)
        return isIn
    }

    /**
     *
     * 测试when表达式
     */
    fun testWhen() {
        var condition = 100
        when (condition) {
            in 60..80 -> Log.d("fanhy", "及格")
            in 80..90 -> Log.d("fanhy", "良好")
            in 90..100 -> Log.d("fanhy", "优秀")
            else -> Log.d("fanhy", "不及格")
        }
    }

    /**
     *
     * when表达式中使用is 判断对象类型
     */
    fun testWhenResult(param: Any) = when (param) {
        is String -> param.endsWith(".jpg", true)
        else -> false
    }

    /**
     *
     * 遍历数组或集合的方式
     */
    fun testIndices(array: IntArray) {
        for (index in array.indices) {
            Log.d("fanhy", "index is $index, value is ${array[index]}")
        }

        for ((index, value) in array.withIndex()) {
            Log.d("fanhy", "index is $index, value is $value")
        }
    }

    /**
     *
     * 告别Java的函数重载啦~~~~~~
     */
    fun testOverload(content: String, isShow: Boolean = true) {
        if (isShow) {
            Log.d("fanhy", content)
        }
    }

    fun testThread() {
        thread {
            Log.d("fanhy", "线程1 id：${Thread.currentThread().id}")
        }

        thread(false) {
            Log.d("fanhy", "线程2 id：${Thread.currentThread().id}")
        }.start()

    }

    /**
     *
     * 测试Kotlin同步锁
     */
    fun testLock() {
        var lock: Lock = ReentrantLock()

        var result1 = lock(lock) {testThread()}

        var result2 = lock(lock, {testThread()})

        fun funParams() = testThread()
        var result3 = lock(lock, ::funParams)
    }

    /**
     *
     * 加同步锁
     */
    fun <T> lock(lock: Lock, body: () -> T ) : T {
        lock.lock()
        try {
            return body()
        }
        finally {
            lock.unlock()
        }
    }

    fun testMap() {
        val list = listOf("zhangsanfeng", "xiaoyaowang", "yinsusu", "zhangcuishan", "zhangwuji")
        list
                .filter { it.startsWith("zhang") }      // 过滤
                .sortedBy { it }    // 排序
                .map { it.replace(it[0], it[0].toUpperCase()) }     // 转换
                .forEach { Log.d("fanhy", it) }     // 遍历
    }

    val name: String by lazy {
        Log.d("fanhy", "executed only first time")
        "Double Thunder"
    }
}
