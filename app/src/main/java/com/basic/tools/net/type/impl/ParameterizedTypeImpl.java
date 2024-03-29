

package com.basic.tools.net.type.impl;

import androidx.annotation.NonNull;

import com.basic.tools.net.type.TypeException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;


/**
 * <pre>
 *     desc   : 泛型类

 *     time   : 2018/4/28 上午12:51
 * </pre>
 */
public class ParameterizedTypeImpl implements ParameterizedType {
    private final Class raw;
    private final Type[] args;
    private final Type owner;

    public ParameterizedTypeImpl(Class raw, Type[] args, Type owner) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
        this.owner = owner;
        checkArgs();
    }

    private void checkArgs() {
        if (this.raw == null) {
            throw new TypeException("raw class can\'t be null");
        } else {
            TypeVariable[] typeParameters = raw.getTypeParameters();
            if (this.args.length != 0 && typeParameters.length != args.length) {
                throw new TypeException(raw.getName() + " expect " + typeParameters.length + " arg(s), got " + args.length);
            }
        }
    }

    @NonNull
    @Override
    public Type[] getActualTypeArguments() {
        return this.args;
    }

    @NonNull
    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {
        return owner;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(raw.getName());
        if (args.length != 0) {
            sb.append('<');

            for (int i = 0; i < args.length; ++i) {
                if (i != 0) {
                    sb.append(", ");
                }

                Type type = args[i];
                if (!(type instanceof Class)) {
                    sb.append(args[i].toString());
                } else {
                    Class clazz = (Class) type;
                    if (!clazz.isArray()) {
                        sb.append(clazz.getName());
                    } else {
                        int count = 0;

                        do {
                            ++count;
                            clazz = clazz.getComponentType();
                        } while (clazz.isArray());

                        sb.append(clazz.getName());

                        for (int j = count; j > 0; --j) {
                            sb.append("[]");
                        }
                    }
                }
            }

            sb.append('>');
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && o instanceof ParameterizedTypeImpl) {
            ParameterizedTypeImpl that = (ParameterizedTypeImpl) o;
            return raw.equals(that.raw) && (Arrays.equals(args, that.args) && (owner != null ? owner.equals(that.owner) : that.owner == null));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = raw.hashCode();
        result = 31 * result + Arrays.hashCode(args);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
