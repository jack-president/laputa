package com.laputa.foundation.web.kendo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JiangDongPing on 2016/11/25.
 */
public class DataSourceRequest {
    private Integer page;

    private Integer pageSize;

    private Integer take;

    private Integer skip;

    private List<SortDescriptor> sort;

    private List<GroupDescriptor> group;

    private List<AggregateDescriptor> aggregate;

    private FilterDescriptor filter;

    public DataSourceRequest() {
        filter = new FilterDescriptor();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTake() {
        return take;
    }

    public void setTake(Integer take) {
        this.take = take;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public List<SortDescriptor> getSort() {
        return sort;
    }

    public void setSort(List<SortDescriptor> sort) {
        this.sort = sort;
    }

    public List<GroupDescriptor> getGroup() {
        return group;
    }

    public void setGroup(List<GroupDescriptor> group) {
        this.group = group;
    }

    public List<AggregateDescriptor> getAggregate() {
        return aggregate;
    }

    public void setAggregate(
            List<AggregateDescriptor> aggregate) {
        this.aggregate = aggregate;
    }

    public FilterDescriptor getFilter() {
        return filter;
    }

    public void setFilter(FilterDescriptor filter) {
        this.filter = filter;
    }

    public static class SortDescriptor {
        private String field;

        private String dir;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }
    }

    public static class GroupDescriptor extends SortDescriptor {
        private List<AggregateDescriptor> aggregates;

        public GroupDescriptor() {
            aggregates = new ArrayList<AggregateDescriptor>();
        }

        public List<AggregateDescriptor> getAggregates() {
            return aggregates;
        }
    }

    public static class AggregateDescriptor {
        private String field;

        private String aggregate;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getAggregate() {
            return aggregate;
        }

        public void setAggregate(String aggregate) {
            this.aggregate = aggregate;
        }
    }

    public static class FilterDescriptor {
        private String logic;

        private List<FilterDescriptor> filters;

        private String field;

        private Object value;

        private String operator;

        private boolean ignoreCase = true;

        public FilterDescriptor() {
            filters = new ArrayList<FilterDescriptor>();
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getLogic() {
            return logic;
        }

        public void setLogic(String logic) {
            this.logic = logic;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public List<FilterDescriptor> getFilters() {
            return filters;
        }
    }
}
