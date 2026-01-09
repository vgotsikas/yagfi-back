package com.github.regyl.gfi.service.impl.issue;

import com.github.regyl.gfi.dto.github.IssueGraphQlResponseDto;
import com.github.regyl.gfi.dto.github.GithubPageInfo;
import com.github.regyl.gfi.dto.github.GithubSearchDto;
import com.github.regyl.gfi.model.IssueRequestDto;
import com.github.regyl.gfi.model.LabelModel;
import com.github.regyl.gfi.service.issue.IssueSourceService;
import com.github.regyl.gfi.service.label.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GithubIssueSourceServiceImpl implements IssueSourceService {

    private final GraphQlClient githubClient;
    private final LabelService labelService;

    public void upload() {
        Collection<LabelModel> labels = labelService.findAll();
        for (LabelModel label : labels) {
            String query = String.format("is:issue is:open label:\"%s\"", label.getTitle());
        }


        GithubSearchDto search = response.getData().getSearch();
        GithubPageInfo pageInfo = search.getPageInfo();
    }

    private IssueGraphQlResponseDto getIssues(IssueRequestDto dto) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("query", dto.getQuery());
        variables.put("cursor", dto.getCursor());
        return githubClient.document("""
                            query SearchIssuesByLabel($query: String!, $cursor: String) {
                              rateLimit {
                                cost
                                remaining
                                resetAt
                              }
                              search(query: $query, type: ISSUE, first: 100, after: $cursor) {
                                pageInfo {
                                  hasNextPage
                                  endCursor
                                }
                                nodes {
                                  ... on Issue {
                                    id
                                    number
                                    title
                                    url
                                    state
                                    updatedAt
                                    repository {
                                        nameWithOwner
                                        url
                                        description
                                        primaryLanguage {
                                            id
                                            name
                                        }
                                        updatedAt
                                    }
                                    labels(first: 20) {
                                        nodes {
                                            name
                                        }
                                    }
                                  }
                                }
                              }
                            }
                            """)
                .variables(variables)
                .executeSync()
                .toEntity(IssueGraphQlResponseDto.class);
    }
}
