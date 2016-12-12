def answer(s):
	ascii_min = 97  # 'a'
	ascii_max = 122  # 'z'

	decoded_string = ''

	for character in s:
		ascii_value = ord(character)

		if ascii_max >= ascii_value >= ascii_min:
			decoded_character = chr(((ascii_value - ascii_max) * -1) + ascii_min)
			decoded_string += decoded_character
		else:
			decoded_string += character

	return decoded_string


def main():
	# Expected output: "did you see last night's episode?"

	output = answer("wrw blf hvv ozhg mrtsg'h vkrhlwv?")

	print(output)

if __name__ == '__main__':
	main()
