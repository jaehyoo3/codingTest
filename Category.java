import java.util.*;

public class Category {
    private int id;
    private String name;
    private int parentId;
    private List<Category> children;

    public Category(int id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getParentId() {
        return parentId;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void addChild(Category category) {
        children.add(category);
    }

    public static Category createCategoryTree(List<Category> categories) {
        Map<Integer, Category> categoryMap = new HashMap<>();
        Category rootCategory = null;

        for (Category category : categories) {
            categoryMap.put(category.getId(), category);

            if (category.getParentId() == 0) {
                rootCategory = category;
            } else {
                Category parentCategory = categoryMap.get(category.getParentId());
                if (parentCategory != null) {
                    parentCategory.addChild(category);
                }
            }
        }

        return rootCategory;
    }

    public static Category findCategoryByName(Category category, String categoryName) {
        if (category.getName().equals(categoryName)) {
            return category;
        }

        for (Category childCategory : category.getChildren()) {
            Category foundCategory = findCategoryByName(childCategory, categoryName);
            if (foundCategory != null) {
                return foundCategory;
            }
        }

        return null;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":").append(id).append(",");
        sb.append("\"name\":\"").append(name).append("\",");
        sb.append("\"parentId\":").append(parentId);

        if (!children.isEmpty()) {
            sb.append(",\"children\":[");
            for (int i = 0; i < children.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(children.get(i).toJson());
            }
            sb.append("]");
        }

        sb.append("}");
        return sb.toString();
    }
}
