package zc.free.acg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("zc.free.acg.mapper")//扫描mapper包
public class AcgApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcgApplication.class, args);
    }
}
