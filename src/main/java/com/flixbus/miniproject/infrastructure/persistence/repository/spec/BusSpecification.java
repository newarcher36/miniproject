package com.flixbus.miniproject.infrastructure.persistence.repository.spec;

import com.flixbus.miniproject.domain.bus.BusType;
import com.flixbus.miniproject.domain.bus.Color;
import com.flixbus.miniproject.domain.bus.spec.SearchCriteria;
import com.flixbus.miniproject.infrastructure.persistence.entity.BusEntity;
import com.flixbus.miniproject.infrastructure.persistence.entity.DepotEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BusSpecification implements Specification<BusEntity> {

    private final SearchCriteria criteria;

    public BusSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<BusEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("==")) {

            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue().toString() + "%");

            } else if (root.get(criteria.getKey()).getJavaType() == BusType.class) {
                return builder.equal(root.get(criteria.getKey()), BusType.resolve(criteria.getValue().toUpperCase()));

            } else if (root.get(criteria.getKey()).getJavaType() == Color.class) {
                return builder.equal(root.get(criteria.getKey()), Color.resolve(criteria.getValue().toUpperCase()));

            } else if (root.get(criteria.getKey()).getJavaType() == DepotEntity.class) {
                return builder.like(root.join("depot").get("name"), "%" + criteria.getValue() + "%");
            }
        }
        return null;
    }
}
