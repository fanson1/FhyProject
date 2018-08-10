package com.base.demo.test

import android.util.Log
import android.view.View
import com.base.demo.test.extention.BaseClass
import com.base.demo.test.extention.Data
import com.base.demo.test.extention.User
import com.base.demo.test.oop.Person
import com.base.demo.test.oop.Student


/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-10 14:09
 */
class UseKotlin {
    fun start() {

        var test = Test()

//        var a = testParams(test)// 没有返回值也可以定义变量接收：返回的是Unit相当于java中的void
//        Log.d("fanhy", "a：" + a)
//        testReturn(test)
//        testLoop(test)
//        testIs(test)
//        test.testBaseType()
//        testIf(test)
//        testWhen(test)

//        testOOP()
//        testExtention()
//        testCopy()

//        testSealted()
//        testOverload(test)
//        test.testThread()
        test.testMap()
        test.name
        test.name

        var map = HashMap<String, String>()
        map["name"] = "张三"
        map["sex"] = "男"
    }

    private fun testOverload(test: Test) {
        test.testOverload("打印一句话：Hello，world 1", true)
        test.testOverload("打印一句话：Hello，world 2", false)
        test.testOverload("打印一句话：Hello，world 3")
    }

    /**
     *
     * 测试密封类
     */
    private fun testSealted() {
        test(BaseClass.Test1())
        test(BaseClass.Test2())
        test(BaseClass.Test3)
    }

    /**
     * 使用when表达式
     */
    fun test(instance: BaseClass)=when(instance){
        is BaseClass.Test1-> instance.test()
        is BaseClass.Test2-> instance.test()
        is BaseClass.Test3->instance.test()
    }

    private fun testCopy() {
        var data = Data("李四", 20)
        Log.d("fanhy", "data = " + data)
        var newData = data.copy()
        Log.d("fanhy", "newData = " + newData)
        var newData2 = data.copy("李四光")
        Log.d("fanhy", "newData2 = " + newData2)
    }

    private fun testExtention() {
        var user = User("张三丰")
        /**
         * 扩展函数
         */
        fun User.eat(what: String) {
            Log.d("fanhy", "${user.name}正在吃$what")
        }
        user.eat("叉烧包")
    }

    private fun testOOP() {
        var person = Person()
        person.pid = "007"
        person.age = -10
        person.pName = "默罕默德.阿扎西.多特雷瓦.伯德基"
        person.sex = '男'
        Log.d("fanhy", "个人信息1：" + person)

        var person2 = Person("008", "卡扎菲", 69, '男')
        Log.d("fanhy", "个人信息2：" + person2)

        var stu = Student("008", "卡扎菲", 69, '男')
        // 匿名对象
        stu.setOnMyClickListener(object : Student.OnMyClickListener {
            override fun onMyClick(view: View) {
            }

            override fun default() {
                super.default()
            }
        })
    }

    private fun testWhen(test: Test) {
        test.testWhen()
        Log.d("fanhy", "是否以.jpg结尾:" + test.testWhenResult("13232131.jpg"))
        Log.d("fanhy", "是否以.jpg结尾:" + test.testWhenResult("13232131.png"))
        Log.d("fanhy", "是否以.jpg结尾:" + test.testWhenResult(110))
        test.testIndices(intArrayOf(1, 2, 4, 5, 6))
    }

    private fun testIf(test: Test) {
        var a = 13
        var b = 11
        Log.d("fanhy", "取$a" + "和$b" + "中较大值：" + test.getMax(a, b))
        b = 15
        Log.d("fanhy", "取$a" + "和$b" + "中较大值：" + test.getMax(a, b))
        test.isInArrange(3, 1, 10)
    }

    private fun testIs(test: Test) {
        val sum = test.sum(10, 13)
        Log.d("fanhy", "sum = $sum")
        Log.d("fanhy", "Lily -> " + test.getNameLength("Lily"))
        Log.d("fanhy", "null -> " + test.getNameLength(null))

        Log.d("fanhy", "" + test.getStrLength("Hello"))
        Log.d("fanhy", "" + test.getStrLength(123))
    }

    private fun testLoop(test: Test) {
        test.testForArrange(10, 20)
        test.testForArrange(10, 20, 3)
        test.testForArrange()
        test.testForArrange(false)
        test.testForArrange(true)
    }

    private fun testReturn(test: Test) {
        val testReturn1 = test.testReturn(source = "Hello")
        var testReturn2 = test.testReturn("Hello")
        var testReturn3 = test.testReturn2(null)// 运行返回null和空参
        Log.d("fanhy", "testReturn1：" + testReturn1)
        Log.d("fanhy", "testReturn2：" + testReturn2)
        Log.d("fanhy", "testReturn3：" + testReturn3)
    }

    private fun testParams(test: Test) {
        var a = test.testParams("张三丰", 125, false)// 没有返回值也可以定义变量接收：返回的是Unit相当于java中的void
        var map = HashMap<String, String>()
        map.put("name", "Lucy")
        map.put("age", "21")
        map.put("sex", "male")
        test.testParams(map)
        test.testChangeParam("乔峰", "虚竹", "段誉", "无崖子", "扫地僧")
        return a
    }

    private fun testVar(): Test {
        var test = Test()
        test.testVar()
        test.testVal()
        test.testStrRefrence()
        test.testNull()
        return test
    }
}
