package com.drools.test;

import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("test.drl"), ResourceType.DRL);
        Collection<KnowledgePackage> collection = kb.getKnowledgePackages();
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(collection);
        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
        List<People> list = getStus();
        for (People people : list) {
            statefulSession.insert(people);
        }
        statefulSession.fireAllRules();
        statefulSession.dispose();

    }

    public static List<People> getStus() {
        List<People> peoples = new ArrayList<>();
        peoples.add(new People("张三", 15, "男"));
        peoples.add(new People("李四", 35, "男"));
        peoples.add(new People("王五", 30, "男"));
        peoples.add(new People("赵六", 20, "男"));

        peoples.add(new People("孙七", 20, "女"));
        peoples.add(new People("周八", 30, "女"));
        return peoples;
    }
}