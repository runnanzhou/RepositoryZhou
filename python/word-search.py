# Name: Runnan Zhou 18Summer CSC120
# Section: 1C
# purpose: This function is designed to find the legal words from
# a given grid which is consist of several letter in a square. What we do
# is to find the word in several ways such as from the left-right or
# right-left, bottom-up, up-bottom, diagonally. if we found the legal
# word, it will print the result one to line.
def main():
    '''
    This function is a summarize function which is consist of calling
    of other functions, which is a clear vision of the function i have
    used in this project.
    :return:
    '''
    list_all, list_grid, list_word = organize_fuction()
    find_hor(list_all, list_grid, list_word)
    find_vertical(list_all, list_grid, list_word)
    find_dia(list_all, list_grid, list_word)
    print_function(list_all)


def print_function(list_all):
    '''
    this function is to sort the list of all the legal words it have found,
    and print the result one per line without any extra whitespace.
    :param list_all: the list of legal words.
    :return:
    '''
    list_all.sort()
    for line in list_all:
        print(line)


def find_dia(list_all, list_grid, list_word):
    '''
    this function is to find the legal words by searching in a diagonal way.
    But, specifically, in this part, it just considering one situation that is
    from the top left to the top right.it build the list of the result of
    searching. Then, we use the find_hori function to judge whehter the word
    is legal or not.
    :param list_all:  the list of legal words.
    :param list_grid: given list of list
    :param list_word: given list of words.
    :return:
    '''
    list_dia = []
    for t in range(len(list_grid)):
        list_grid[t].reverse()
    for r in range(len(list_grid)):
        list_dia.append([])
        for b in range(len(list_grid) - r):
            list_dia[r].append(list_grid[b + r][b])
    for e in range(1, len(list_grid)):
        list_dia.append([])
        for q in range(len(list_grid) - e):
            list_dia[e + len(list_grid) - 1].append(list_grid[q][q + e])
    for u in range(len(list_dia)):
        find_hori(u, list_all, list_dia, list_word)


def find_vertical(list_all, list_grid, list_word):
    '''
    this function is to find the legal words by searching in a vertical way. In
    both way include from the up to bottom and bottom to up. After it found the
    list of words, it judge by a find_hori function. i used a reverse function
    to make the program shorter, which can save much time.
    :param list_all: the list of legal words.
    :param list_grid: given list of list
    :param list_word: given list of words.
    :return:
    '''
    list_verti = []
    for j in range(len(list_grid)):
        list_verti.append([])
        for k in range(len(list_grid[j])):
            list_verti[j].append(list_grid[k][j])
    for p in range(len(list_verti)):
        find_hori(p, list_all, list_verti, list_word)
    for t in range(len(list_verti)):
        list_verti[t].reverse()
        find_hori(t, list_all, list_verti, list_word)


def find_hor(list_all, list_grid, list_word):
    '''
    This function is the collection of the calling of find_hori
    which is to find the word by searching in a left-right or right-left
    way. if it get the leagl words, it put it into a list
    :param list_all: the list of legal words.
    :param list_grid: given list of list
    :param list_word:given list of words.
    :return:
    '''
    for i in range(len(list_grid)):
        find_hori(i, list_all, list_grid, list_word)
    for h in range(len(list_grid)):
        list_grid[h].reverse()
        find_hori(h, list_all, list_grid, list_word)


def organize_fuction():
    '''
    This function is to organize the information from the outside
    resource and deal with them. It make the information into the list
    form which is very important to make the function run.
    :return:  list_all: the list of legal words.
              list_grid: given list of list
              list_word:given list of words.
    '''
    word_name = input()
    grid_name = input()
    list_word = []
    list_grid = []
    list_all = []
    s = ""
    word_file = open(word_name)
    word_file = word_file.readlines()
    for line in word_file:
        list_word.append(line.strip().lower())
    grid_file = open(grid_name)
    grid_file = grid_file.readlines()
    for line in grid_file:
        line = line.lower()
        line = line.split()
        list_grid.append(line)
    return list_all, list_grid, list_word


def find_hori(i, list_all, list_grid, list_word):
    '''
    this function is the core function of this project as i thought. Because
    whatever i need to do , i just organize the list of list and use this
    function to slove the problem. It's very efficient compared to other
    solution.
    :param i: the range number
    :param list_all: the list of legal words.
    :param list_grid: given list of list
    :param list_word: given list of words.
    :return:
    '''
    for n in range(len(list_grid[i])):
        for l in range(3, (len(list_grid[i]) - n)+1):
            s = "".join(list_grid[i][n:n + l])
            if s in list_word:
                list_all.append(s)


main()

