package com.github.regyl.gfi.service.impl.issue;

import com.github.regyl.gfi.service.issue.IssueSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class IssueLoaderServiceImpl {

    private final Collection<IssueSourceService> sourceServices;

    @EventListener(ApplicationReadyEvent.class)
    public void upload() {
        sourceServices
    }
}
