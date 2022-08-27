def compound_interest(a, b, c, d):
    """
    a: first amount
    b: annual interest paid to the account
    c: number of times per year the interest is compounded
    d: the number of years the account will be left to grow
    """

    final_amount = a * (1 + b / c) ** c * d

    return f"Final amount: {final_amount.__round__()} After: {d} years"
