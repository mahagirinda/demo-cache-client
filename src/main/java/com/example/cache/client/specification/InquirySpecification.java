package com.example.cache.client.specification;

import com.telkomsigma.eclears.app.common.viewentity.inquiry.ViewInquiryInstruction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class InquirySpecification {

	public static final String dateformatUi = "dd/MM/yyyy";

    public static Specification<ViewInquiryInstruction> inquiryInstructionSpec(final HashMap<String, String> filter) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            SimpleDateFormat formatter = new SimpleDateFormat(dateformatUi);

//            for (Map.Entry<String, String> field : filter.entrySet()) {
//                String key = field.getKey();
//                String value = (field.getValue() == null) ? null : field.getValue();
//
//                try {
//                    if (value != null && !value.isEmpty()) {
//                        if (key.equals("settlementPeriodFrom")) {
//                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.<Date>get("settlementDate"),
//                                    formatter.parse(value)));
//                        } else if (key.equals("settlementPeriodTo")) {
//                            predicate.getExpressions().add(
//                                    cb.lessThanOrEqualTo(root.<Date>get("settlementDate"), formatter.parse(value)));
//                        } else if (key.equals("cashAmountFrom")) {
//                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(
//                                    root.<BigDecimal>get("qtyAmount"), new BigDecimal(value)));
//                        } else if (key.equals("cashAmountTo")) {
//                            predicate.getExpressions().add(
//                                    cb.lessThanOrEqualTo(root.<BigDecimal>get("qtyAmount"), new BigDecimal(value)));
//                        } else if (key.equals("externalReference")) {
//                            predicate.getExpressions().add(cb.like(root.<String>get(key), value + "%"));
//                        } else if (key.equals("counterpartCode")) {
//                            List<String> relatedMember = Arrays.asList(value.split(","));
//                            if (relatedMember.size() > 1) {
//                                predicate.getExpressions()
//                                        .add(cb.or(cb.isNull(root.get(key)), (root.get(key)).in(relatedMember)));
//                            } else {
//                                predicate.getExpressions().add(cb.like(root.<String>get(key), "%" + value + "%"));
//                            }
//                        } else if (key.equals("securityCodeType")) {
//                            if ("LOCAL".equals(value)) {
//                                predicate.getExpressions().add(cb.or(cb.isNull(root.get(key)),
//                                        cb.like(root.<String>get(key), "%" + value + "%")));
//                            } else {
//                                predicate.getExpressions().add(cb.like(root.<String>get(key), "%" + value + "%"));
//                            }
//                        } else {
//                            predicate.getExpressions().add(cb.like(root.<String>get(key), "%" + value + "%"));
//                        }
//                    }
//                } catch (Exception e) {
//                    log.error(e.getMessage());
//                }
//            }
            return predicate;
        };
    }
}
