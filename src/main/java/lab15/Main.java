package lab15;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("ЛАБОРАТОРНАЯ РАБОТА №15");
        System.out.println("Тема: Разработка, отладка и испытание программ с использованием Git и GitHub");
        System.out.println("Вариант: 8");
        System.out.println("================================================================================\n");

        GitRepository repository = new GitRepository("okp-course-labs");
        LabManager manager = new LabManager(repository, 8, "dashevst");

        List<LabAssignment> labs = new ArrayList<>();
        labs.add(new LabAssignment(1, "Базовый синтаксис Java", 8, "Типы данных и переменные"));
        labs.add(new LabAssignment(2, "Управляющие конструкции", 8, "Условные операторы и циклы"));
        labs.add(new LabAssignment(3, "Работа со строками", 8, "Класс String и форматирование"));
        labs.add(new LabAssignment(4, "Основы ООП", 8, "Классы и методы"));
        labs.add(new LabAssignment(5, "Инкапсуляция", 8, "Конструкторы и геттеры/сеттеры"));
        labs.add(new LabAssignment(6, "Наследование", 8, "Иерархия классов"));
        labs.add(new LabAssignment(7, "Абстрактные классы", 8, "Абстракция данных"));
        labs.add(new LabAssignment(8, "Пакеты и видимость", 8, "Модульность кода"));
        labs.add(new LabAssignment(9, "Перечисления Enum", 8, "Константы и структуры"));
        labs.add(new LabAssignment(10, "Интерфейсы", 8, "Growable, Blooming, Tree, Flower"));
        labs.add(new LabAssignment(11, "Полиморфизм", 8, "Music, RockMusic, ClassicalMusic"));
        labs.add(new LabAssignment(12, "Коллекции и Generics", 8, "Building, Game, Map, List"));
        labs.add(new LabAssignment(13, "Обработка исключений", 8, "Restaurant, Custom Exceptions"));
        labs.add(new LabAssignment(14, "Файловый ввод/вывод", 8, "School info, File I/O, Numbers"));
        labs.add(new LabAssignment(15, "Система контроля версий Git", 8, "Git flow, Pull Requests, Merge"));

        manager.transferAllLabs(labs);

        System.out.println("--- 1. ВЕТКИ РЕПОЗИТОРИЯ ---");
        repository.getBranches().forEach((name, branch) -> {
            System.out.println("  * " + branch);
        });
        System.out.println();

        System.out.println("--- 2. ИСТОРИЯ PULL REQUESTS ---");
        for (PullRequest pr : repository.getPullRequests()) {
            System.out.println("  " + pr);
        }
        System.out.println();

        System.out.println("--- 3. ИСТОРИЯ КОММИТОВ (GIT LOG GRAPH) ---");
        for (Commit commit : repository.getCommits()) {
            String marker = commit.isMergeCommit() ? " [MERGE COMMIT]" : "";
            System.out.printf("  * %s%s\n", commit, marker);
        }
        System.out.println();

        System.out.println("Всего коммитов: " + repository.getCommits().size());
        System.out.println("Всего веток: " + repository.getBranches().size());
        System.out.println("Всего Pull Requests: " + repository.getPullRequests().size());
        System.out.println("Текущая ветка HEAD: " + repository.getCurrentBranch().getName());
        System.out.println("\nЛабораторная работа №15 успешно выполнена!");
    }
}
