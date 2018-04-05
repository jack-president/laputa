package com.laputa.foundation.web.kendo.specification;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.laputa.foundation.persistence.entity.IdEntity;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;

/**
 * Created by JiangDongPing on 2016/11/25.
 */
public class LaputaKendoSpecification<T extends IdEntity> implements Specification<T> {

    protected DataSourceRequest dataSourceRequest;

    protected Map<String, Join> joinMap = new HashMap<>();

    protected Boolean fetchFilter = Boolean.FALSE;

    public LaputaKendoSpecification(DataSourceRequest dataSourceRequest) {
        this.dataSourceRequest = dataSourceRequest;
    }

    public Boolean getFetchFilter() {
        return fetchFilter;
    }

    public void setFetchFilter(Boolean fetchFilter) {
        this.fetchFilter = fetchFilter;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = filter(root, query, cb, this.dataSourceRequest.getFilter());
        if (isCountQuery(query)) {
            return predicate;
        }
        if (predicate != null) {
            query.where(predicate);
        }
        sort(root, query, cb);

        joinMap.clear();
        return query.getRestriction();
    }

    protected Boolean isCountQuery(CriteriaQuery<?> query) {
        if (query.getResultType() == java.lang.Long.class) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Predicate filter(
            Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, DataSourceRequest.FilterDescriptor filter) {
        if (filter != null) {
            List<DataSourceRequest.FilterDescriptor> filters = filter.getFilters();
            if (filters != null && !filters.isEmpty()) {
                if (!filter.getFilters().isEmpty()) {

                    List<Predicate> predicateList = new ArrayList<>();
                    for (DataSourceRequest.FilterDescriptor entry : filters) {
                        if (!entry.getFilters().isEmpty()) {
                            predicateList.add(filter(root, query, cb, entry));
                        } else {
                            predicateList.add(restrict(root, query, cb, entry));
                        }
                    }

                    Predicate[] predicateArray = predicateList.toArray(new Predicate[predicateList.size()]);
                    if (filter.getLogic().equals("or")) {
                        return cb.or(predicateArray);
                    } else {
                        return cb.and(predicateArray);
                    }
                }
            }
        }
        return null;
    }

    public static String[] nestClazzSplit(String clazzName) {
        String sp[] = clazzName.split("Relation");
        int lastCap = 0;
        for (int i = 0; i < sp[0].length(); ++i) {
            if (sp[0].charAt(i) >= 'A' && sp[0].charAt(i) <= 'Z') {
                lastCap = i;
            }
        }
        return new String[]{sp[0].substring(0, lastCap), sp[1], sp[0].substring(lastCap, sp[0].length())};
    }

    private Method takeGetMethod(Class clazz, String filedName) {
        String methodName = "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    private Predicate restrict(
            Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, DataSourceRequest.FilterDescriptor filter) {
        Class<?> clazz = root.getJavaType();
        String operator = filter.getOperator();
        String field = filter.getField();
        Object value = filter.getValue();
        Comparable comparable = value != null ? value.toString() : null;
        boolean ignoreCase = filter.isIgnoreCase();

        Path path = root.get(field);

        try {
            Class<?> type = new PropertyDescriptor(field, clazz).getPropertyType();
            if (type == double.class || type == Double.class) {
                comparable = Double.parseDouble(value.toString());
            } else if (type == float.class || type == Float.class) {
                comparable = Float.parseFloat(value.toString());
            } else if (type == long.class || type == Long.class) {
                comparable = Long.parseLong(value.toString());
            } else if (type == int.class || type == Integer.class) {
                comparable = Integer.parseInt(value.toString());
            } else if (type == short.class || type == Short.class) {
                comparable = Short.parseShort(value.toString());
            } else if (type == boolean.class || type == Boolean.class) {
                comparable = Boolean.parseBoolean(value.toString());
            } else if (type == Date.class) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                String input = value.toString();
                comparable = df.parse(input.replace("Z", "UTC"));
            } else if (type.isAssignableFrom(Set.class) || type.isAssignableFrom(List.class)) {
                comparable = Long.parseLong(value.toString());
                Method method = takeGetMethod(clazz, field);
                Type t = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
                Class tClazz = Class.forName(t.toString().substring("class ".length()));

                if (tClazz.getSimpleName().indexOf("Relation") > 1) {
                    String array[] = nestClazzSplit(tClazz.getSimpleName());
                    String relationClazz = array[0];
                    if (clazz.getSimpleName().equals(relationClazz)) {
                        relationClazz = array[1];
                    }
                    return cb.equal(takeJoin(root, query, field).get(relationClazz.substring(0, 1).toLowerCase() +
                            relationClazz.substring(1) +
                            "Id"), Long.parseLong(value.toString()));
                }
                return cb.equal(takeJoin(root, query, field).get("id"), Long.parseLong(value.toString()));
            }

        } catch (IntrospectionException e) {
        } catch (NumberFormatException nfe) {
        } catch (ParseException e) {
        } catch (ClassNotFoundException e) {
        }

        switch (operator) {
            case "eq":
                if (value == null) {
                    return path.isNull();
                } else {
                    if (comparable instanceof Date) {
                        //return cb.equal(path,"2016-12-6 15:04");
                    }
                    return cb.equal(path, comparable);
                }
            case "neq":
                if (value == null) {
                    return path.isNotNull();
                } else {
                    return cb.or(cb.notEqual(path, comparable), path.isNull());
                }
            case "gt":
                if (comparable instanceof Number) {
                    return cb.gt(path, (Number) comparable);
                } else if (comparable instanceof Date) {
                    return cb.greaterThan(path, comparable);
                } else if (comparable instanceof String) {
                    return cb.greaterThan(path, comparable);
                } else {
                    throw new RuntimeException();
                }
            case "gte":
                if (comparable instanceof Number) {
                    return cb.greaterThanOrEqualTo(path, comparable);
                } else if (comparable instanceof Date) {
                    return cb.greaterThanOrEqualTo(path, comparable);
                } else if (comparable instanceof String) {
                    return cb.greaterThanOrEqualTo(path, comparable);
                } else {
                    throw new RuntimeException();
                }
            case "lt":
                if (comparable instanceof Number) {
                    return cb.lt(path, (Number) comparable);
                } else if (comparable instanceof Date) {
                    return cb.lessThan(path, comparable);
                } else if (comparable instanceof String) {
                    return cb.lessThan(path, comparable);
                } else {
                    throw new RuntimeException();
                }
            case "lte":
                if (comparable instanceof Number) {
                    return cb.lessThanOrEqualTo(path, comparable);
                } else if (comparable instanceof Date) {
                    return cb.lessThanOrEqualTo(path, comparable);
                } else if (comparable instanceof String) {
                    return cb.lessThanOrEqualTo(path, comparable);
                } else {
                    throw new RuntimeException();
                }
            case "startswith":
                if (ignoreCase) {
                    return cb.like(cb.lower(path), comparable.toString().toLowerCase() + "%");
                } else {
                    return cb.like(path, comparable + "%");
                }
            case "endswith":
                if (ignoreCase) {
                    return cb.like(cb.lower(path), "%" + comparable.toString());
                } else {
                    return cb.like(path, "%" + comparable);
                }
            case "contains":
                if (ignoreCase) {
                    return cb.like(cb.lower(path), "%" + comparable.toString() + "%");
                } else {
                    return cb.like(path, "%" + comparable + "%");
                }
            case "doesnotcontain":
                if (ignoreCase) {
                    return cb.or(cb.notLike(cb.lower(path), "%" + comparable.toString() + "%"), path.isNull());
                } else {
                    return cb.or(cb.notLike(path, "%" + comparable + "%"), path.isNull());
                }
        }
        return null;
    }

    private void sort(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (this.dataSourceRequest.getSort() != null && !this.dataSourceRequest.getSort().isEmpty()) {
            List<Order> orderList = new ArrayList<>(this.dataSourceRequest.getSort().size());
            for (DataSourceRequest.SortDescriptor entry : this.dataSourceRequest.getSort()) {
                if (entry.getDir().equalsIgnoreCase("asc")) {
                    orderList.add(cb.asc(takeFieldPath(root, entry.getField())));
                } else if (entry.getDir().equalsIgnoreCase("desc")) {
                    orderList.add(cb.desc(takeFieldPath(root, entry.getField())));
                }
            }
            query.orderBy(orderList);
        }
    }

    public Pageable pageable() {
        if (this.dataSourceRequest.getPage() != null && this.dataSourceRequest.getPageSize() != null) {
            Pageable page = PageRequest.of(this.dataSourceRequest.getPage() - 1, this.dataSourceRequest.getPageSize());
            return page;
        } else {
            return Pageable.unpaged();
        }
    }

    private Join takeJoin(Root<T> root, CriteriaQuery<?> query, String field) {
        if (joinMap.get(field) == null) {
            query.distinct(true);
            if (!isCountQuery(query) && fetchFilter) {
                joinMap.put(field, root.join(field, JoinType.LEFT));
            } else {
                joinMap.put(field, root.join(field, JoinType.LEFT));
            }
        }
        return joinMap.get(field);
    }

    private Path takeFieldPath(Root<T> root, String field) {
        String[] fieldSplit = field.split("\\.");
        Path path = null;
        for (String f : fieldSplit) {
            if (path == null) {
                path = root.get(f);
            } else {
                path = path.get(f);
            }
        }
        return path;
    }
}
