package com.jq.child.student.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ClassPayoutExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassPayoutExample() {
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

        public Criteria andPayoutIdIsNull() {
            addCriterion("payout_id is null");
            return (Criteria) this;
        }

        public Criteria andPayoutIdIsNotNull() {
            addCriterion("payout_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayoutIdEqualTo(Integer value) {
            addCriterion("payout_id =", value, "payoutId");
            return (Criteria) this;
        }

        public Criteria andPayoutIdNotEqualTo(Integer value) {
            addCriterion("payout_id <>", value, "payoutId");
            return (Criteria) this;
        }

        public Criteria andPayoutIdGreaterThan(Integer value) {
            addCriterion("payout_id >", value, "payoutId");
            return (Criteria) this;
        }

        public Criteria andPayoutIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("payout_id >=", value, "payoutId");
            return (Criteria) this;
        }

        public Criteria andPayoutIdLessThan(Integer value) {
            addCriterion("payout_id <", value, "payoutId");
            return (Criteria) this;
        }

        public Criteria andPayoutIdLessThanOrEqualTo(Integer value) {
            addCriterion("payout_id <=", value, "payoutId");
            return (Criteria) this;
        }

        public Criteria andPayoutIdIn(List<Integer> values) {
            addCriterion("payout_id in", values, "payoutId");
            return (Criteria) this;
        }

        public Criteria andPayoutIdNotIn(List<Integer> values) {
            addCriterion("payout_id not in", values, "payoutId");
            return (Criteria) this;
        }

        public Criteria andPayoutIdBetween(Integer value1, Integer value2) {
            addCriterion("payout_id between", value1, value2, "payoutId");
            return (Criteria) this;
        }

        public Criteria andPayoutIdNotBetween(Integer value1, Integer value2) {
            addCriterion("payout_id not between", value1, value2, "payoutId");
            return (Criteria) this;
        }

        public Criteria andClassNumIsNull() {
            addCriterion("class_num is null");
            return (Criteria) this;
        }

        public Criteria andClassNumIsNotNull() {
            addCriterion("class_num is not null");
            return (Criteria) this;
        }

        public Criteria andClassNumEqualTo(String value) {
            addCriterion("class_num =", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumNotEqualTo(String value) {
            addCriterion("class_num <>", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumGreaterThan(String value) {
            addCriterion("class_num >", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumGreaterThanOrEqualTo(String value) {
            addCriterion("class_num >=", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumLessThan(String value) {
            addCriterion("class_num <", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumLessThanOrEqualTo(String value) {
            addCriterion("class_num <=", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumLike(String value) {
            addCriterion("class_num like", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumNotLike(String value) {
            addCriterion("class_num not like", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumIn(List<String> values) {
            addCriterion("class_num in", values, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumNotIn(List<String> values) {
            addCriterion("class_num not in", values, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumBetween(String value1, String value2) {
            addCriterion("class_num between", value1, value2, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumNotBetween(String value1, String value2) {
            addCriterion("class_num not between", value1, value2, "classNum");
            return (Criteria) this;
        }

        public Criteria andOutTypeIsNull() {
            addCriterion("out_type is null");
            return (Criteria) this;
        }

        public Criteria andOutTypeIsNotNull() {
            addCriterion("out_type is not null");
            return (Criteria) this;
        }

        public Criteria andOutTypeEqualTo(String value) {
            addCriterion("out_type =", value, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeNotEqualTo(String value) {
            addCriterion("out_type <>", value, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeGreaterThan(String value) {
            addCriterion("out_type >", value, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeGreaterThanOrEqualTo(String value) {
            addCriterion("out_type >=", value, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeLessThan(String value) {
            addCriterion("out_type <", value, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeLessThanOrEqualTo(String value) {
            addCriterion("out_type <=", value, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeLike(String value) {
            addCriterion("out_type like", value, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeNotLike(String value) {
            addCriterion("out_type not like", value, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeIn(List<String> values) {
            addCriterion("out_type in", values, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeNotIn(List<String> values) {
            addCriterion("out_type not in", values, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeBetween(String value1, String value2) {
            addCriterion("out_type between", value1, value2, "outType");
            return (Criteria) this;
        }

        public Criteria andOutTypeNotBetween(String value1, String value2) {
            addCriterion("out_type not between", value1, value2, "outType");
            return (Criteria) this;
        }

        public Criteria andOutNumIsNull() {
            addCriterion("out_num is null");
            return (Criteria) this;
        }

        public Criteria andOutNumIsNotNull() {
            addCriterion("out_num is not null");
            return (Criteria) this;
        }

        public Criteria andOutNumEqualTo(Integer value) {
            addCriterion("out_num =", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumNotEqualTo(Integer value) {
            addCriterion("out_num <>", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumGreaterThan(Integer value) {
            addCriterion("out_num >", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("out_num >=", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumLessThan(Integer value) {
            addCriterion("out_num <", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumLessThanOrEqualTo(Integer value) {
            addCriterion("out_num <=", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumIn(List<Integer> values) {
            addCriterion("out_num in", values, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumNotIn(List<Integer> values) {
            addCriterion("out_num not in", values, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumBetween(Integer value1, Integer value2) {
            addCriterion("out_num between", value1, value2, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumNotBetween(Integer value1, Integer value2) {
            addCriterion("out_num not between", value1, value2, "outNum");
            return (Criteria) this;
        }

        public Criteria andOptTimeIsNull() {
            addCriterion("opt_time is null");
            return (Criteria) this;
        }

        public Criteria andOptTimeIsNotNull() {
            addCriterion("opt_time is not null");
            return (Criteria) this;
        }

        public Criteria andOptTimeEqualTo(Date value) {
            addCriterionForJDBCDate("opt_time =", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("opt_time <>", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("opt_time >", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("opt_time >=", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeLessThan(Date value) {
            addCriterionForJDBCDate("opt_time <", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("opt_time <=", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeIn(List<Date> values) {
            addCriterionForJDBCDate("opt_time in", values, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("opt_time not in", values, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("opt_time between", value1, value2, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("opt_time not between", value1, value2, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptParentIsNull() {
            addCriterion("opt_parent is null");
            return (Criteria) this;
        }

        public Criteria andOptParentIsNotNull() {
            addCriterion("opt_parent is not null");
            return (Criteria) this;
        }

        public Criteria andOptParentEqualTo(String value) {
            addCriterion("opt_parent =", value, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentNotEqualTo(String value) {
            addCriterion("opt_parent <>", value, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentGreaterThan(String value) {
            addCriterion("opt_parent >", value, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentGreaterThanOrEqualTo(String value) {
            addCriterion("opt_parent >=", value, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentLessThan(String value) {
            addCriterion("opt_parent <", value, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentLessThanOrEqualTo(String value) {
            addCriterion("opt_parent <=", value, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentLike(String value) {
            addCriterion("opt_parent like", value, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentNotLike(String value) {
            addCriterion("opt_parent not like", value, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentIn(List<String> values) {
            addCriterion("opt_parent in", values, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentNotIn(List<String> values) {
            addCriterion("opt_parent not in", values, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentBetween(String value1, String value2) {
            addCriterion("opt_parent between", value1, value2, "optParent");
            return (Criteria) this;
        }

        public Criteria andOptParentNotBetween(String value1, String value2) {
            addCriterion("opt_parent not between", value1, value2, "optParent");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceIsNull() {
            addCriterion("out_invoice is null");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceIsNotNull() {
            addCriterion("out_invoice is not null");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceEqualTo(String value) {
            addCriterion("out_invoice =", value, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceNotEqualTo(String value) {
            addCriterion("out_invoice <>", value, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceGreaterThan(String value) {
            addCriterion("out_invoice >", value, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceGreaterThanOrEqualTo(String value) {
            addCriterion("out_invoice >=", value, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceLessThan(String value) {
            addCriterion("out_invoice <", value, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceLessThanOrEqualTo(String value) {
            addCriterion("out_invoice <=", value, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceLike(String value) {
            addCriterion("out_invoice like", value, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceNotLike(String value) {
            addCriterion("out_invoice not like", value, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceIn(List<String> values) {
            addCriterion("out_invoice in", values, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceNotIn(List<String> values) {
            addCriterion("out_invoice not in", values, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceBetween(String value1, String value2) {
            addCriterion("out_invoice between", value1, value2, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutInvoiceNotBetween(String value1, String value2) {
            addCriterion("out_invoice not between", value1, value2, "outInvoice");
            return (Criteria) this;
        }

        public Criteria andOutDetailIsNull() {
            addCriterion("out_detail is null");
            return (Criteria) this;
        }

        public Criteria andOutDetailIsNotNull() {
            addCriterion("out_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOutDetailEqualTo(String value) {
            addCriterion("out_detail =", value, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailNotEqualTo(String value) {
            addCriterion("out_detail <>", value, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailGreaterThan(String value) {
            addCriterion("out_detail >", value, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailGreaterThanOrEqualTo(String value) {
            addCriterion("out_detail >=", value, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailLessThan(String value) {
            addCriterion("out_detail <", value, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailLessThanOrEqualTo(String value) {
            addCriterion("out_detail <=", value, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailLike(String value) {
            addCriterion("out_detail like", value, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailNotLike(String value) {
            addCriterion("out_detail not like", value, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailIn(List<String> values) {
            addCriterion("out_detail in", values, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailNotIn(List<String> values) {
            addCriterion("out_detail not in", values, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailBetween(String value1, String value2) {
            addCriterion("out_detail between", value1, value2, "outDetail");
            return (Criteria) this;
        }

        public Criteria andOutDetailNotBetween(String value1, String value2) {
            addCriterion("out_detail not between", value1, value2, "outDetail");
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