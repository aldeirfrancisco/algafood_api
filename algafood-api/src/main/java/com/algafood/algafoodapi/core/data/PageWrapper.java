package com.algafood.algafoodapi.core.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PageWrapper<T> extends PageImpl<T> {

    private Pageable peageable;

    public PageWrapper(Page page, Pageable pegeable) {
        super(page.getContent(), pegeable, page.getTotalElements());
        this.peageable = pegeable;
    }

    @Override
    public Pageable getPageable() {
        return this.peageable;
    }
}
