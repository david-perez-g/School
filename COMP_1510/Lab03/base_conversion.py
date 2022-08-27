def base_conversion(num, base):
    decomposed_num = []
    counter = -1
    result = 0

    for i in str(num):
        decomposed_num.append(int(i))  # Adding elements to list

    for index in range(1, len(decomposed_num) + 1):
        counter += 1
        decomposed_num[-index] = decomposed_num[-index] * base**counter  # Multiplying every element for their corresponding base

    for index in range(len(decomposed_num)):  # Adding to result every element of the list to get the final result
        result += decomposed_num[index]

    return result


print(base_conversion(5342, 10))