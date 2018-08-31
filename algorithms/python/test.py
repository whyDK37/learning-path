from pip import CommandError

valid_commands = {'start', 'stop', 'pause'}

while True:
    command = input('> ')
    if command.lower() not in valid_commands:
        raise CommandError('Invalid commmand: %s' % command)
