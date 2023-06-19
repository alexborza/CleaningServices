package com.cleaning.infrastructure;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Component
public class AppointmentProcessorScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentProcessorScheduler.class);

    @Autowired
    private AppointmentProcessService appointmentProcessService;

    @Scheduled(fixedDelay = 30000)
    public void processNotCompletedAppointments() {
        LOGGER.info("Starting processing of not completed appointments");
        appointmentProcessService.processNotCompletedAppointments();
    }

}
