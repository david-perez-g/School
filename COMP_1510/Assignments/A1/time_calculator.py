def time_calculator(seconds):

    minutes = seconds // 60
    hours = seconds // 3600
    days = seconds // 86400

    return f"Minutes: {minutes} Hours: {hours} Days: {days}"


time_calculator(1000000)
