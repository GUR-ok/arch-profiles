package ru.gur.archprofiles.web.filter;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@Component
@RequestScope
public class RequestScopedDataProvider {

    private Integer value = 0;
}
