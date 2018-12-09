package ru.nik66.crudservlet.servlet;

import ru.nik66.crudservlet.Validate;
import ru.nik66.crudservlet.ValidateService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ActionType {

    private final Map<String, Function<String, Boolean>> dispath = new HashMap<>();

    private final Map<String, String[]> params;
    private final Validate logic = ValidateService.getInstance();

    public ActionType(Map<String, String[]> params) {
        this.params = params;
    }

    public ActionType init() {
        this.load("add", this.doAdd());
        this.load("update", this.doUpdate());
        this.load("delete", this.doDelete());
        return this;
    }

    public boolean doAction(String s) {
        return this.dispath.get(s).apply(s);
    }

    private void load(String s, Function<String, Boolean> a) {
        this.dispath.put(s, a);
    }

    private Function<String, Boolean> doAdd() {
        return s -> this.logic.add(
                this.params.get("name")[0],
                this.params.get("login")[0],
                this.params.get("password")[0],
                this.params.get("role")[0],
                this.params.get("email")[0],
                this.params.get("country")[0],
                this.params.get("city")[0]
        );
    }

    private Function<String, Boolean> doUpdate() {
        return s -> this.logic.update(
                Integer.parseInt(this.params.get("id")[0]),
                this.params.get("name")[0],
                this.params.get("login")[0],
                this.params.get("password")[0],
                this.params.get("role")[0],
                this.params.get("email")[0],
                this.params.get("country")[0],
                this.params.get("city")[0]
        );
    }

    private Function<String, Boolean> doDelete() {
        return s -> this.logic.delete(Integer.parseInt(this.params.get("id")[0]));
    }

}
