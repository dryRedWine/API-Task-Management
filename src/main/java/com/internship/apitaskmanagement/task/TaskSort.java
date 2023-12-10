package com.internship.apitaskmanagement.task;


import com.internship.apitaskmanagement.enums.Order;
import com.internship.apitaskmanagement.error.exceptions.CustomValidationException;
import com.internship.apitaskmanagement.utility.FromSizeRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskSort {

    public static Pageable sortByCreatedDate(Integer from, Integer size, String sortOrder) {
        if (sortOrder.equalsIgnoreCase(Order.CREATED_DATE_ASC.getOrder()))
            return FromSizeRequest.of(from, size, Sort.by("creation_date").ascending());
        else if (sortOrder.equalsIgnoreCase(Order.CREATED_DATE_DESC.getOrder()))
            return FromSizeRequest.of(from, size, Sort.by("creation_date").descending());
            throw new CustomValidationException("Значение сортировки заданно не верно.");
    }
}
