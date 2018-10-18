package ru.geekbrains.java2.lesson3;

import java.util.HashMap;
import java.util.Iterator;

public class MyHashMap<K, V> extends HashMap<K, V> {

        @Override
        public String toString() {
            Iterator<Entry<K,V>> i = entrySet().iterator();
            if (! i.hasNext())
                return "{}";

            StringBuilder sb = new StringBuilder();
            sb.append('{');
            for (;;) {
                Entry<K,V> e = i.next();
                K key = e.getKey();
                V value = e.getValue();
                sb.append(key   == this ? "(this Map)" : key);
                sb.append(" встречается ");
                sb.append(value == this ? "(this Map)" : value+" раз(а)");
                if (! i.hasNext())
                    return sb.append('}').toString();
                sb.append(',').append(' ');
            }

        }
    }

