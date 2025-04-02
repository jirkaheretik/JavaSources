package cz.braza.leetcode;

import java.util.*;

/*
Daily 21.3.2025 - https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/
2115. Find All Possible Recipes from Given Supplies
Medium
Topics
Companies
Hint
You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. A recipe can also be an ingredient for other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.



Example 1:

Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
Example 2:

Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
Example 3:

Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich","burger"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".


Constraints:

n == recipes.length == ingredients.length
1 <= n <= 100
1 <= ingredients[i].length, supplies.length <= 100
1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
All the values of recipes and supplies combined are unique.
Each ingredients[i] does not contain any duplicate values.
 */
public class L2115FindRecipesFromSupplies {
    /*
    Make set of supplies and map of recipes to indices, then check if we can make each recipe,
    and add it among supplies if so.
    Runs: 22ms, beats 99.8%, 113 testcases
     */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> result = new ArrayList<>();
        // init:
        Set<String> suppliesSet = new HashSet<>();
        HashMap<String, Integer> recipesSet = new HashMap<>();
        boolean[] visited = new boolean[recipes.length];
        for (String supply : supplies) suppliesSet.add(supply);
        for (int i = 0; i < recipes.length; i++) recipesSet.put(recipes[i], i);

        // process recipes:
        for (int idx = 0; idx < recipes.length; idx++)
            if (canMake(ingredients, recipesSet, visited, idx, suppliesSet, recipes)) result.add(recipes[idx]);
        return result;
    }

    private boolean canMake(final List<List<String>> ingredients, final HashMap<String, Integer> recipesSet, boolean[] visited, int idx, Set<String> suppliesSet, final String[] recipes) {
        // if we already visited this recipe, we know the result
        if (visited[idx]) return suppliesSet.contains(recipes[idx]);
        // otherwise, get list of ingredients and check them all
        visited[idx] = true;
        List<String> needed = ingredients.get(idx);
        for (String neededIngredient : needed)
            if (!suppliesSet.contains(neededIngredient))
                if (recipesSet.containsKey(neededIngredient)) {
                    int key = recipesSet.get(neededIngredient);
                    if (visited[key]) return false;
                    // check if we can make needed recipe:
                    if (!canMake(ingredients, recipesSet, visited, key, suppliesSet, recipes)) return false;
                } else return false; // we don't get that ingredient at all
        suppliesSet.add(recipes[idx]);
        return true;
    }
}
