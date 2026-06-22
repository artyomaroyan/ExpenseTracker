package main.java.persistence;

import main.java.model.Expense;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 22:49:57
 */
public class ModelInformationImpl extends AbstractModelInformation<Expense, Long> {

    public ModelInformationImpl() {
        super(Expense.class);
    }

    @Override
    public Long getID(Expense model) {
        return model.id();
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }
}