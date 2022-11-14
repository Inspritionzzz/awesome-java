import com.bupt.awesomejava.scaffold.spring.service.MyServiceImpl

beans {
    myServiceImpl02(MyServiceImpl) {
        id  = 123;
        name = "user create by groovy"
    }
}
