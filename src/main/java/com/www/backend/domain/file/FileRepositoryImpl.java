package com.www.backend.domain.file;

import com.www.backend.common.repository.BaseRepositoryImpl;

import javax.persistence.EntityManager;
import java.util.UUID;

public class FileRepositoryImpl extends BaseRepositoryImpl<File, UUID> implements FileRepository {

    public FileRepositoryImpl(EntityManager em) {
        super(File.class, em);
    }
}
