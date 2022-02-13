package pl.javastart.equipy.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> readAllNames() {
        return categoryRepository.findAll()
                .stream()
                .map(u -> u.getName())
                .collect(Collectors.toList());
    }
}
