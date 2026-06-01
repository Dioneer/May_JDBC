package org.example.mapper;

public interface Mapper<K,F>{
    K create(F from);
}
