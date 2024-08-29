package com.craftinginterpreters.lox;

class AstPrinter implements Expr.Visitor<String> {
    String print(Expr expr){
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr){
        return parenthsize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitThisExpr(Expr.This expr){
        return expr.keyword.lexeme;
    }

    @Override
    public String visitSuperExpr(Expr.Super expr){
        return expr.keyword.lexeme;
    }

    @Override
    public String visitGetExpr(Expr.Get expr){
        return expr.toString();
    }

    @Override
    public String visitSetExpr(Expr.Set expr){
        return expr.toString();
    }

    @Override
    public String visitCallExpr(Expr.Call expr){
        return expr.toString();
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr){
        return parenthsize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr){
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr){
        return parenthsize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr){
        return parenthsize(expr.operator.lexeme, expr.right);
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr){
        return expr.name.toString();
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr){
        return expr.name.toString();
    }

    private String parenthsize(String name, Expr... exprs){
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for(Expr expr : exprs){
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

}
