package io.github.lgatodu47.screenshot_viewer.config;

import net.minecraft.network.chat.Component;

import java.io.File;
import java.util.Comparator;

public enum SortingCriterion {
    NAME,
    DATE,
    TYPE,
    SIZE;

    public String firstLetter() {
        return name().substring(0, 1).toUpperCase();
    }

    public Component nameComponent() {
        return Component.translatable("sorting_criterion.screenshot_viewer." + name().toLowerCase());
    }

    public Comparator<File> fileComparator() {
        return switch (this) {
            case NAME -> Comparator.comparing(File::getName);
            case DATE -> Comparator.comparing(File::lastModified).thenComparing(File::getName);
            case TYPE -> Comparator.comparing(SortingCriterion::getExtension).thenComparing(File::getName);
            case SIZE -> Comparator.comparing(File::length).thenComparing(File::getName);
        };
    }

    private static String getExtension(File file) {
        String name = file.getName();
        int i = name.lastIndexOf('.');
        if(i < 0) {
            return "";
        }
        return name.substring(i + 1);
    }
}
