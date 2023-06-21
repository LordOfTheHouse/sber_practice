package ru.sber;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sber.config.AppConfig;
import ru.sber.exception.UserIsNotAClientOfTheBank;
import ru.sber.model.Client;
import ru.sber.model.DataForTransfer;
import ru.sber.service.ApplicationService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationService applicationService = context.getBean(ApplicationService.class);
        try {
            applicationService.transferPayment(new Client("Sergei", 1),
                    new DataForTransfer("89535121588", BigDecimal.valueOf(1000), LocalDate.now()
                    ));
            applicationService.transferPayment(new Client("Sergei", 5),
                    new DataForTransfer("89535121588", BigDecimal.valueOf(1000), LocalDate.now()
                    ));
        }catch (UserIsNotAClientOfTheBank er){
            er.printStackTrace();
        }catch (Exception ex){
            System.out.println("Неизвестная ошибка");
        }

    }
}