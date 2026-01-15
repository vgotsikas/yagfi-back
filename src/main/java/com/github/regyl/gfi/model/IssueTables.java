package com.github.regyl.gfi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IssueTables {

    FIRST("e_issue_1", "e_repository_1"),

    SECOND("e_issue_2", "e_repository_2"),

    ;

    private final String issueTableName;
    private final String repoTableName;

    public static IssueTables getDifferent(IssueTables tableName) {
        if (tableName == FIRST) {
            return SECOND;
        } else {
            return FIRST;
        }
    }
}
