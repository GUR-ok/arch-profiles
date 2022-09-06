package ru.gur.archprofiles.web.filter;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@SessionScope
public class SessionScopedDataProvider {

    private Integer value = 0;
}
