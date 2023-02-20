package com.example.mysimplenotes.ui.updatenote

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mysimplenotes.R
import com.example.mysimplenotes.databinding.FragmentUpdateNoteBinding
import com.example.mysimplenotes.model.Note
import com.example.mysimplenotes.ui.MainActivity
import com.example.mysimplenotes.ui.viewmodel.NoteViewModel

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {
    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateNoteFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNoteBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.edtNoteBodyUpdate.setText(currentNote.body)
        binding.edtNoteTitleUpdate.setText(currentNote.title)

        binding.fabDone.setOnClickListener {
            val title = binding.edtNoteTitleUpdate.text.toString().trim()
            val body = binding.edtNoteBodyUpdate.text.toString().trim()

            if (title.isNotEmpty()) {
                noteViewModel.updateNote(
                    Note(currentNote.id, title, body, currentNote.color, noteViewModel.getCurrentDate())
                )
                Toast.makeText(
                    requireContext(),
                    "Note updated!",
                    Toast.LENGTH_SHORT
                ).show()
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            } else {
               Toast.makeText(
                   requireContext(),
                   "ERROR: Enter the title",
                   Toast.LENGTH_SHORT
               ).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_menu -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        AlertDialog.Builder(requireActivity()).apply {
            setTitle("Delete Note")
            setMessage("Are you sure you want to permanently delete this note?")
            setPositiveButton("DELETE") { _, _ ->
                noteViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(
                    R.id.action_updateNoteFragment_to_homeFragment
                )
            }
            setNegativeButton("CANCEL", null)
        }.create().show()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}