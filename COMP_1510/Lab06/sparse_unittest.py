from unittest import TestCase
from unittest import main


def convert_sparse_vec_to_dict(sparse_vec):
    """Converts a sparse vector in a dictionary.

    :param sparse_vec: a list
    precondition: a list
    postcondition: correctly returns a dictionary with the sparse vector elements
    :return: a dictionary
    >>> convert_sparse_vec_to_dict([1, 0, 0, 0, 1, 3, 0, 0, 0, 1, 0, 0, 1])
    {0: 1, 4: 1, 5: 3, 9: 1, 12: 1}
    """
    result = {}
    for element in sparse_vec:
        if element == 0:
            continue
        if sparse_vec.count(element) > 1 and sparse_vec.index(element) in result.keys():  # This line detects if the element is repeated
            sparse_vec[sparse_vec.index(element)] = 0

        result[sparse_vec.index(element)] = element

    return result


def sparse_add(first_sparse_vector, second_sparse_vector):
    """Calculate the sum of two vectors.

    :param first_sparse_vector: dictionary
    :param second_sparse_vector: dictionary
    precondition: two dictionaries
    postcondition: correctly returns a new dictionary with the sum of the other dictionary´s elements
    :return: a new dictionary
    >>> sparse_add({2: 1, 5: 1, 7: 1, 9: 1}, {3: 6, 4: 3, 7: 1, 9: 2, 11: 7})
    {'sum': [7, 4, 2, 3, 7]}
    """
    new_dic = {}
    results = []
    first_sparse_vector_values = [i for i in first_sparse_vector.values()]
    second_sparse_vector_values = [i for i in second_sparse_vector.values()]

    def get_biggest_list(first_list, second_list):
        if len(first_list) > len(second_list):
            return [len(first_list), 'first']
        elif len(first_list) == len(second_list):
            return [len(first_list)]
        else:
            return [len(second_list), 'second']

    bigger = get_biggest_list(first_sparse_vector_values, second_sparse_vector_values)

    for i in range(0, bigger[0]):
        try:
            results.append(first_sparse_vector_values[i] + second_sparse_vector_values[i])
        except IndexError:
            if bigger[1] == 'first':
                results.append(first_sparse_vector_values[i])
            else:
                results.append(second_sparse_vector_values[i])

    new_dic['sum'] = results
    return new_dic


def sparse_length(sparse_dic, extra_zeros):
    """Returns the length of a sparse vector converted in a dictionary.

    :param sparse_dic: sparse vector converted in a dictionary.
    :param extra_zeros: extra zeros after the last non-zero element.
    precondition: the sparse vector converted in a dictionary and the extra zeros after the last non-zero element.
    postcondition: correctly calculates the length of the original sparse vector list.
    :return: length of a sparse vector
    >>> sparse_length({2: 1, 5: 1, 7: 1, 9: 1}, 2)
    12
    """
    sparse_len = []
    for num in sparse_dic.keys():
        sparse_len.append(num)

    sparse_len.sort()
    return sparse_len[-1] + 1 + extra_zeros


class SparseTest(TestCase):
    def test_convert_sparse_vec_to_dict(self):
        self.assertEqual(convert_sparse_vec_to_dict([0, 2, 0, 0, 0, 1, 0]), {1: 2, 5: 1})

    def test_sparse_add(self):
        self.assertEqual(sparse_add({3: 2, 5: 7}, {1: 1, 4: 3, 9: 1}), {'sum': [3, 10, 1]})

    def test_sparse_length(self):
        self.assertEqual(sparse_length({0: 1, 6: 3}, 1), 8)


if __name__ == '__main__':
    main()
