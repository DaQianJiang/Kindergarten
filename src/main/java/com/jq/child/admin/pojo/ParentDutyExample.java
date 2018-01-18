package com.jq.child.admin.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ParentDutyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ParentDutyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andParentdutyTimeIsNull() {
            addCriterion("parentduty_time is null");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeIsNotNull() {
            addCriterion("parentduty_time is not null");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeEqualTo(Date value) {
            addCriterionForJDBCDate("parentduty_time =", value, "parentdutyTime");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("parentduty_time <>", value, "parentdutyTime");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("parentduty_time >", value, "parentdutyTime");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("parentduty_time >=", value, "parentdutyTime");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeLessThan(Date value) {
            addCriterionForJDBCDate("parentduty_time <", value, "parentdutyTime");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("parentduty_time <=", value, "parentdutyTime");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeIn(List<Date> values) {
            addCriterionForJDBCDate("parentduty_time in", values, "parentdutyTime");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("parentduty_time not in", values, "parentdutyTime");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("parentduty_time between", value1, value2, "parentdutyTime");
            return (Criteria) this;
        }

        public Criteria andParentdutyTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("parentduty_time not between", value1, value2, "parentdutyTime");
            return (Criteria) this;
        }

        public Criteria andDutyClassIsNull() {
            addCriterion("duty_class is null");
            return (Criteria) this;
        }

        public Criteria andDutyClassIsNotNull() {
            addCriterion("duty_class is not null");
            return (Criteria) this;
        }

        public Criteria andDutyClassEqualTo(String value) {
            addCriterion("duty_class =", value, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassNotEqualTo(String value) {
            addCriterion("duty_class <>", value, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassGreaterThan(String value) {
            addCriterion("duty_class >", value, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassGreaterThanOrEqualTo(String value) {
            addCriterion("duty_class >=", value, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassLessThan(String value) {
            addCriterion("duty_class <", value, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassLessThanOrEqualTo(String value) {
            addCriterion("duty_class <=", value, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassLike(String value) {
            addCriterion("duty_class like", value, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassNotLike(String value) {
            addCriterion("duty_class not like", value, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassIn(List<String> values) {
            addCriterion("duty_class in", values, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassNotIn(List<String> values) {
            addCriterion("duty_class not in", values, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassBetween(String value1, String value2) {
            addCriterion("duty_class between", value1, value2, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyClassNotBetween(String value1, String value2) {
            addCriterion("duty_class not between", value1, value2, "dutyClass");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameIsNull() {
            addCriterion("duty_parentname is null");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameIsNotNull() {
            addCriterion("duty_parentname is not null");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameEqualTo(String value) {
            addCriterion("duty_parentname =", value, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameNotEqualTo(String value) {
            addCriterion("duty_parentname <>", value, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameGreaterThan(String value) {
            addCriterion("duty_parentname >", value, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameGreaterThanOrEqualTo(String value) {
            addCriterion("duty_parentname >=", value, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameLessThan(String value) {
            addCriterion("duty_parentname <", value, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameLessThanOrEqualTo(String value) {
            addCriterion("duty_parentname <=", value, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameLike(String value) {
            addCriterion("duty_parentname like", value, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameNotLike(String value) {
            addCriterion("duty_parentname not like", value, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameIn(List<String> values) {
            addCriterion("duty_parentname in", values, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameNotIn(List<String> values) {
            addCriterion("duty_parentname not in", values, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameBetween(String value1, String value2) {
            addCriterion("duty_parentname between", value1, value2, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andDutyParentnameNotBetween(String value1, String value2) {
            addCriterion("duty_parentname not between", value1, value2, "dutyParentname");
            return (Criteria) this;
        }

        public Criteria andExt1IsNull() {
            addCriterion("ext1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("ext1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("ext1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("ext1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("ext1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("ext1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("ext1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("ext1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("ext1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("ext1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("ext1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("ext1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("ext1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt2IsNull() {
            addCriterion("ext2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("ext2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("ext2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("ext2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("ext2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("ext2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("ext2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("ext2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("ext2 not like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("ext2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("ext2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("ext2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("ext2 not between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt3IsNull() {
            addCriterion("ext3 is null");
            return (Criteria) this;
        }

        public Criteria andExt3IsNotNull() {
            addCriterion("ext3 is not null");
            return (Criteria) this;
        }

        public Criteria andExt3EqualTo(String value) {
            addCriterion("ext3 =", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotEqualTo(String value) {
            addCriterion("ext3 <>", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThan(String value) {
            addCriterion("ext3 >", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThanOrEqualTo(String value) {
            addCriterion("ext3 >=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThan(String value) {
            addCriterion("ext3 <", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThanOrEqualTo(String value) {
            addCriterion("ext3 <=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Like(String value) {
            addCriterion("ext3 like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotLike(String value) {
            addCriterion("ext3 not like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3In(List<String> values) {
            addCriterion("ext3 in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotIn(List<String> values) {
            addCriterion("ext3 not in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Between(String value1, String value2) {
            addCriterion("ext3 between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotBetween(String value1, String value2) {
            addCriterion("ext3 not between", value1, value2, "ext3");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}